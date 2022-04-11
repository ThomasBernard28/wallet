package com.example.Api.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bank")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService){
        this.bankService = bankService;
    }

    @GetMapping(path = "{bic}")
    public Bank getBank(@PathVariable("bic") String bic){
        return bankService.getBank(bic);
    }

    @GetMapping
    public List<Bank> getBanks(){
        return bankService.getBanks();
    }
}
