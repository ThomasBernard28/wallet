package com.example.Api.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
