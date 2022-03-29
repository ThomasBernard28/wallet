package com.example.Api.client;

import com.example.Api.bank.Bank;
import com.example.Api.bank.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final BankRepository bankRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, BankRepository bankRepository){
        this.clientRepository = clientRepository;
        this.bankRepository = bankRepository;
    }

    public List<Client> getAllBankClients(String bic){
        if(!bankRepository.findBankByBic(bic).isPresent()){
            throw new EntityNotFoundException("This insitution doesn't exist");
        }
        return clientRepository.findAllByBank(bic);
    }

    public Optional<Client> getOneClient(String bic, String userID) throws EntityNotFoundException{
        Optional<Client> clientVsInstOptional = clientRepository.findByBankAndUserID(bic, userID);

        if(!clientVsInstOptional.isPresent()){
            throw new EntityNotFoundException("This client doesn't exist");
        }

        return clientVsInstOptional;
    }

    public void registerClient(Bank bank, String userID){
        if(clientRepository.findByBankAndUserID(bank.getBic(), userID).isPresent()){
            throw new IllegalStateException("This client already exists");
        }

        Client clientVsInst = new Client(new ClientIDEmb(bank.getBic(), userID));
        System.out.println("Client created");

        clientRepository.save(clientVsInst);
        System.out.println("Client saved");
    }

    public void deleteClient(Bank bank, String userID){

        Optional<Client> clientOptional = clientRepository.findByBankAndUserID(bank.getBic(), userID);

        if(!clientOptional.isPresent()){
            throw new EntityNotFoundException("This client does not exist");
        }
        clientRepository.delete(clientOptional.get());
    }
}
