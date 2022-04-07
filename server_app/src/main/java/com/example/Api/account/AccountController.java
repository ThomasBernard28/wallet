package com.example.Api.account;

import com.example.Api.bank.BankService;
import com.example.Api.client.Client;
import com.example.Api.client.ClientService;
import com.example.Api.wallet.Wallet;
import com.example.Api.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/accounts")
public class AccountController {

    private final AccountService accountService;
    private final WalletService walletService;
    private final ClientService clientService;
    private final BankService bankService;

    @Autowired
    public AccountController(AccountService accountService, WalletService walletService, ClientService clientService, BankService bankService){
        this.accountService = accountService;
        this.walletService = walletService;
        this.clientService = clientService;
        this.bankService = bankService;
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
        String iban = json.get("iban");
        try{
            clientService.getOneClient(json.get("bic"), json.get("userID"));

        }catch(EntityNotFoundException e){
            clientService.registerClient(bankService.getBank(json.get("bic")), json.get("userID"));
        }

        try{
            accountService.getIbanAccount(iban).getIban().equals(iban);

        }catch (EntityNotFoundException e){
            Wallet wallet = walletService.getWalletByWalletID(Long.parseLong(json.get("walletID"))).get();
            Client client = clientService.getOneClient(json.get("bic"), json.get("userID")).get();

            accountService.registerAccount(iban, wallet, client, json.get("type"), Float.parseFloat(json.get("avgBalance")), json.get("localCurr"), Integer.parseInt(json.get("activity")));
        }
    }

    @PutMapping(path = "{iban}")
    public void updateAccount(
            @PathVariable("iban") String iban,
            @RequestParam(required = false) Integer activity,
            @RequestParam(required = false) Float avgBalance
    ){
        accountService.updateAccount(iban, activity, avgBalance);
    }

}
