package com.example.Api.client;

import com.example.Api.bank.Bank;
import com.example.Api.bank.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientVsInstService {

    private final ClientVsInstRepository clientVsInstRepository;
    private final BankRepository bankRepository;

    @Autowired
    public ClientVsInstService(ClientVsInstRepository clientVsInstRepository, BankRepository bankRepository){
        this.clientVsInstRepository = clientVsInstRepository;
        this.bankRepository = bankRepository;
    }

    public List<ClientVsInst> getAllBankClients(String bic){
        if(!bankRepository.findBankByBic(bic).isPresent()){
            throw new EntityNotFoundException("This insitution doesn't exist");
        }
        return clientVsInstRepository.findAllByBank(bic);
    }

    public ClientVsInst getOneClient(String bic, String userID){
        Optional<ClientVsInst> clientVsInstOptional = clientVsInstRepository.findByBankAndUserID(bic, userID);

        if(!clientVsInstOptional.isPresent()){
            throw new EntityNotFoundException("This client doesn't exist");
        }

        return clientVsInstOptional.get();
    }

    public void registerClient(String bic, String userID){
        if(clientVsInstRepository.findByBankAndUserID(bic, userID).isPresent()){
            throw new IllegalStateException("This client already exists");
        }
        Optional<Bank> bank = bankRepository.findBankByBic(bic);
        if (!bank.isPresent()){
            throw new EntityNotFoundException("Please enter a correct bank");
        }

        ClientVsInst clientVsInst = new ClientVsInst(bank.get(), userID);

        clientVsInstRepository.save(clientVsInst);
    }
}
