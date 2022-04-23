package com.example.Api.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Balance Controller
 */
@RestController
@RequestMapping(path = "api/v1/balances")
public class BalanceController {

    private final BalanceService balanceService;

    /**
     * Constructor
     * @param balanceService Balance Service
     */
    @Autowired
    public BalanceController(BalanceService balanceService){
        this.balanceService = balanceService;
    }

    /**
     * Method to catch a GET request asking for all the balances of an Account
     * @param iban the iban of the Account
     * @return A List containing all the different balances
     */
    @GetMapping(path = "{iban}")
    public List<Balance> getBalancesByIban(@PathVariable("iban") String iban){
        return balanceService.getBalancesByIban(iban);
    }

    /**
     * Method to catch a GET request asking for a balance by its balanceID
     * @param balanceID The balanceID of the balance
     * @return the Balance
     */
    @GetMapping(path = "balanceID/{balanceID}")
    public Balance getBalanceByID(@PathVariable("balanceID") Long balanceID){
        return balanceService.getBalanceById(balanceID);
    }

    /**
     * Method to catch a POST request asking to create a new balance
     * @param json the data sent by the client app
     */
    @PostMapping
    public void createBalance(@RequestBody Map<String, String> json){
        //The iban of the account we want to add the balance to
        String iban = json.get("iban");
        //The currency of the new balance
        String currency = json.get("currency");
        //The balance of the Cash Balance
        Float balance = Float.parseFloat(json.get("balance"));

        balanceService.registerBalance(iban, currency, balance);
    }

    /**
     * Method to catch a PUT request asking to make a cash deposit on the balance
     * @param iban the iban of the Account the balance belongs to
     * @param amount the Amount of the deposit
     */
    @PutMapping(path = "deposit/{iban}/{amount}")
    public void cashDeposit(@PathVariable("iban") String iban, @PathVariable("amount") Float amount){

    }
}
