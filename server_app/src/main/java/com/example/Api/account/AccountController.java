package com.example.Api.account;

import com.example.Api.client.Client;
import com.example.Api.client.ClientService;
import com.example.Api.pendingRequests.accountRequest.AccountRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

/**
 * Account Controller
 */
@RestController
@RequestMapping(path = "api/v1/accounts")
public class AccountController {

    private final AccountService accountService;
    private final ClientService clientService;
    private final AccountRequestService accountRequestService;

    /**
     * Constructor
     * @param accountService Account Service
     * @param clientService Client Service
     * @param accountRequestService Account Request Service
     */
    @Autowired
    public AccountController(AccountService accountService,ClientService clientService, AccountRequestService accountRequestService ){
        this.accountService = accountService;
        this.clientService = clientService;
        this.accountRequestService = accountRequestService;
    }

    /**
     * Method to catch a GET request asking for all the accounts of a wallet
     * @param walletID walletID of the Wallet
     * @return a List containing all the Accounts of the wallets
     */
    @GetMapping(path = "{walletId}")
    public List<Account> getAccounts(@PathVariable("walletId") Long walletID){
        return accountService.getAccounts(walletID);
    }

    /**
     * Method to catch a GET request asking for all the Accounts of an Institution
     * @param bic the bic of the Institution
     * @return List containing all accounts of the institution
     */
    @GetMapping(path = "allByBic/{bic}")
    public List<Account> getAllAccountsForInstitution(@PathVariable("bic") String bic){
        return accountService.findAllAccountForBank(bic);
    }

    /**
     * Method to catch a GET request asking for all the Accounts of Client(bic, userID)
     * @param bic the bic of the institution the client is registered in
     * @param userID the userID of the client
     * @return A List containing all the accounts of the client
     */
    @GetMapping(path = "{bic}/{userID}")
    public List<Account> getClientAccounts(@PathVariable("bic") String bic, @PathVariable("userID") String userID){
        return accountService.getAccountsForClient(bic, userID);
    }

    /**
     * Method to catch a GET request asking for an Account by its iban
     * @param iban the iban of the account we want
     * @return The Account
     */
    @GetMapping(path = "iban/{iban}")
    public Account getIbanAccount(@PathVariable("iban") String iban){
        return accountService.getIbanAccount(iban);
    }

    /**
     * Method to catch a POST request asking to create an Account
     * @param json the data sent by the client app
     */
    @PostMapping
    public void createAccount(@RequestBody Map<String , String> json){
        Client client = clientService.getOneClient(json.get("bic"), json.get("userID"));

        //generate an account request
        accountRequestService.registerAccountRequest(client, json.get("type"));
    }

    /**
     * Method to catch a PUT request asking to set an account to inactive
     * @param iban the iban of the account
     * @param json the date sent by the client app
     */
    @PutMapping(path = "{iban}")
    public void updateAccount(
            @PathVariable("iban") String iban,
            @RequestBody Map<String, String> json
    ){
        accountService.updateAccount(iban, Integer.parseInt(json.get("activity")));
    }

}
