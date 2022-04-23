package com.example.Api.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Bank Controller
 */
@RestController
@RequestMapping(path = "api/v1/bank")
public class BankController {

    private final BankService bankService;

    /**
     * Constructor
     * @param bankService Bank Service
     */
    @Autowired
    public BankController(BankService bankService){
        this.bankService = bankService;
    }

    /**
     * Method to catch a GET request asking for a Bank
     * @param bic the bic of the bank
     * @return the Bank asked
     */
    @GetMapping(path = "{bic}")
    public Bank getBank(@PathVariable("bic") String bic){
        return bankService.getBank(bic);
    }

    /**
     * Method to catch a GET request asking for all the Banks
     * @return A List containing all banks
     */
    @GetMapping
    public List<Bank> getBanks(){
        return bankService.getBanks();
    }
}
