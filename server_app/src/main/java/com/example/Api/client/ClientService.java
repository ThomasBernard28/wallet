package com.example.Api.client;

import com.example.Api.bank.Bank;
import com.example.Api.bank.BankRepository;
import com.example.Api.exception.ApiNotFoundException;
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
            throw new ApiNotFoundException("This insitution : "+ bic +" doesn't exist");
        }
        return clientRepository.findAllByBank(bic);
    }

    public Client getOneClient(String bic, String userID){
        Optional<Client> clientVsInstOptional = clientRepository.findByBankAndUserID(bic, userID);

        if(clientVsInstOptional.isEmpty()){
            throw new ApiNotFoundException("This client with userID and bic : " + userID + " " + bic + "doesn't exist");
        }

        return clientVsInstOptional.get();
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
            throw new ApiNotFoundException("This client in the institution : " + bank.getName() + " with userID : " + userID + " does not exist");
        }
        clientRepository.delete(clientOptional.get());
    }
}
