package com.example.Api.client;

import com.example.Api.bank.Bank;
import com.example.Api.bank.BankRepository;
import com.example.Api.exception.ApiNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Client Service
 */
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final BankRepository bankRepository;

    /**
     * Constructor
     * @param clientRepository Client Repository
     * @param bankRepository Bank repository
     */
    @Autowired
    public ClientService(ClientRepository clientRepository, BankRepository bankRepository){
        this.clientRepository = clientRepository;
        this.bankRepository = bankRepository;
    }

    /**
     * Method that get all the client of the bank calling the Client Repository
     * @param bic The bic of bank
     * @return List containing all the clients of the bank
     */
    public List<Client> getAllBankClients(String bic){
        if(!bankRepository.findBankByBic(bic).isPresent()){
            throw new ApiNotFoundException("This insitution : "+ bic +" doesn't exist");
        }
        return clientRepository.findAllByBank(bic);
    }

    /**
     * Method to get one client calling the Client Repository
     * @param bic the bic of the bank
     * @param userID the userID of the client
     * @return A Client if found, an exception else
     */
    public Client getOneClient(String bic, String userID){
        Optional<Client> clientVsInstOptional = clientRepository.findByBankAndUserID(bic, userID);

        //If client not found
        if(clientVsInstOptional.isEmpty()){
            throw new ApiNotFoundException("This client with userID and bic : " + userID + " " + bic + "doesn't exist");
        }
        //else
        return clientVsInstOptional.get();
    }

    /**
     * Method to register a client in the db
     * @param bank A Bank instance
     * @param userID the userID of the new Client
     */
    public void registerClient(Bank bank, String userID){
        //If the client already exists throw an exception
        if(clientRepository.findByBankAndUserID(bank.getBic(), userID).isPresent()){
            throw new IllegalStateException("This client already exists");
        }

        //Else create the client
        Client clientVsInst = new Client(new ClientIDEmb(bank.getBic(), userID));

        clientRepository.save(clientVsInst);
    }

    /**
     * Method to delete a client in the db
     * @param bank Bank instance
     * @param userID the userID of the client to delete
     */
    public void deleteClient(Bank bank, String userID){

        Optional<Client> clientOptional = clientRepository.findByBankAndUserID(bank.getBic(), userID);

        //If the Client does not exist throw an exception
        if(!clientOptional.isPresent()){
            throw new ApiNotFoundException("This client in the institution : " + bank.getName() + " with userID : " + userID + " does not exist");
        }
        clientRepository.delete(clientOptional.get());
    }
}
