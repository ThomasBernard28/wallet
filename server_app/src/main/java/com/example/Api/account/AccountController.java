package com.example.Api.account;

import com.example.Api.client.Client;
import com.example.Api.client.ClientService;
import com.example.Api.pendingRequests.accountRequest.AccountRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/accounts")
public class AccountController {

    private final AccountService accountService;
    private final ClientService clientService;
    private final AccountRequestService accountRequestService;

    @Autowired
    public AccountController(AccountService accountService,ClientService clientService, AccountRequestService accountRequestService ){
        this.accountService = accountService;
        this.clientService = clientService;
        this.accountRequestService = accountRequestService;
    }

    @GetMapping(path = "{walletId}")
    public List<Account> getAccounts(@PathVariable("walletId") Long walletID){
        return accountService.getAccounts(walletID);
    }

    @GetMapping(path = "{bic}/{userID}")
    public List<Account> getClientAccounts(@PathVariable("bic") String bic, @PathVariable("userID") String userID){
        return accountService.getAccountsForClient(bic, userID);
    }

    @GetMapping(path = "iban/{iban}")
    public Account getIbanAccount(@PathVariable("iban") String iban){
        return accountService.getIbanAccount(iban);
    }

    @PostMapping
    public void createAccount(@RequestBody Map<String , String> json){
        Client client = clientService.getOneClient(json.get("bic"), json.get("userID"));

        accountRequestService.registerAccountRequest(client, json.get("type"));
    }

    @PutMapping(path = "{iban}")
    public void updateAccount(
            @PathVariable("iban") String iban,
            @RequestBody Map<String, String> json
    ){
        accountService.updateAccount(iban, Integer.parseInt(json.get("activity")));
    }

}
