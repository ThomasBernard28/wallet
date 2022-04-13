package com.example.Api.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/balances")
public class BalanceController {

    private final BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService balanceService){
        this.balanceService = balanceService;
    }

    @GetMapping(path = "{iban}")
    public List<Balance> getBalancesByIban(@PathVariable("iban") String iban){
        return balanceService.getBalancesByIban(iban);
    }

    @GetMapping(path = "balanceID/{balanceID}")
    public Balance getBalanceByID(@PathVariable("balanceID") Long balanceID){
        return balanceService.getBalanceById(balanceID);
    }

    @PostMapping
    public void createBalance(@RequestBody Map<String, String> json){
        String iban = json.get("iban");
        String currency = json.get("currency");
        Float balance = Float.parseFloat(json.get("balance"));

        balanceService.registerBalance(iban, currency, balance);
    }

    @PutMapping(path = "deposit/{iban}/{amount}")
    public void cashDeposit(@PathVariable("iban") String iban, @PathVariable("amount") Float amount){

    }
}
