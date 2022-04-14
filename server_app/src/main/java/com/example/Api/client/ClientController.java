package com.example.Api.client;

import com.example.Api.bank.Bank;
import com.example.Api.bank.BankRepository;
import com.example.Api.bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/clientVs")
public class ClientController {

    private final ClientService clientService;
    private final BankService bankService;

    @Autowired
    public ClientController(ClientService clientService, BankService bankService){
        this.clientService = clientService;
        this.bankService = bankService;
    }

    @GetMapping(path = "{bic}")
    public List<Client> getInstClients(@PathVariable("bic") String bic){
        return clientService.getAllBankClients(bic);
    }

    @GetMapping(path = "{bic}/{userID}")
    public Client getOneClient(@PathVariable("bic") String bic, @PathVariable("userID") String userID){
        return clientService.getOneClient(bic, userID);
    }

    @PostMapping
    public void registerNewClient(@RequestBody Map<String, String> json){
        String bic = json.get("bic");
        String userID = json.get("userID");

        Bank bank = bankService.getBank(bic);

        clientService.registerClient(bank, userID);
    }

    @DeleteMapping(path = "{bic}/{userID}")
    public void deleteClient(@PathVariable("bic") String bic, @PathVariable("userID") String userID){
        Bank bank = bankService.getBank(bic);

        clientService.deleteClient(bank, userID);
    }
}
