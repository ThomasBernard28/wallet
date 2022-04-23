package com.example.Api.client;

import com.example.Api.bank.Bank;
import com.example.Api.bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * Client Controller to catch HTTP requests
 */
@RestController
@RequestMapping(path = "api/v1/clientVs")
public class ClientController {

    private final ClientService clientService;
    private final BankService bankService;

    /**
     * Constructor
     * @param clientService Client Service
     * @param bankService Bank Service
     */
    @Autowired
    public ClientController(ClientService clientService, BankService bankService){
        this.clientService = clientService;
        this.bankService = bankService;
    }

    /**
     * Method to catch a GET request asking for the clients of an institution
     * @param bic The bic of the Institution/Bank
     * @return A LIst containing all the clients of the institution
     */
    @GetMapping(path = "{bic}")
    public List<Client> getInstClients(@PathVariable("bic") String bic){
        return clientService.getAllBankClients(bic);
    }

    /**
     * Method to catch a GET request asking for a specific Client
     * @param bic The bic of institution the client is registered in
     * @param userID The userID of the client
     * @return The Client
     */
    @GetMapping(path = "{bic}/{userID}")
    public Client getOneClient(@PathVariable("bic") String bic, @PathVariable("userID") String userID){
        return clientService.getOneClient(bic, userID);
    }

    /**
     * Method to catch a POST request to create a new Client
     * @param json The json data sent from the client app
     */
    @PostMapping
    public void registerNewClient(@RequestBody Map<String, String> json){
        String bic = json.get("bic");
        String userID = json.get("userID");

        Bank bank = bankService.getBank(bic);

        clientService.registerClient(bank, userID);
    }

    /**
     * Method to catch a DELETE request for a Client
     * @param bic The bic of the insitution the Client is registered in
     * @param userID The userID of the client
     */
    @DeleteMapping(path = "{bic}/{userID}")
    public void deleteClient(@PathVariable("bic") String bic, @PathVariable("userID") String userID){
        Bank bank = bankService.getBank(bic);

        clientService.deleteClient(bank, userID);
    }
}
