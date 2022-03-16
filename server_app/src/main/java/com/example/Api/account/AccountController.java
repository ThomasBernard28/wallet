package com.example.Api.account;

import com.example.Api.clientVsInstitution.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/accounts")
public class AccountController {

    private final AccountService accountService;
    private final ClientService clientService;

    @Autowired
    public AccountController(AccountService accountService, ClientService clientService){
        this.accountService = accountService;
        this.clientService = clientService;
    }

    @GetMapping(path = "{walletId}")
    public List<Account> getAccounts(@PathVariable("walletId") Long walletID){
        return accountService.getAccounts(walletID);
    }

    @GetMapping(path = "{bic}/{userID}")
    public List<Account> getClientAccounts(@PathVariable("bic") String bic, @PathVariable("userID") String userID){
        return accountService.getAccountsForClient(bic, userID);
    }
}
