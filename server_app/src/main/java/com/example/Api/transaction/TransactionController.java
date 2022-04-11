package com.example.Api.transaction;

import com.example.Api.balance.Balance;
import com.example.Api.balance.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final BalanceService balanceService;

    @Autowired
    public TransactionController(TransactionService transactionService, BalanceService balanceService){
        this.transactionService = transactionService;
        this.balanceService = balanceService;
    }

    @PostMapping
    public void createTransaction(@RequestBody Map<String, String> json){
        LocalDateTime dateTime = LocalDateTime.parse(json.get("dateTime"));
        Integer status = Integer.parseInt(json.get("status"));
        Integer weekend = Integer.parseInt(json.get("weekend"));
        Float amount = Float.parseFloat(json.get("amount"));
        Balance ibanSender = balanceService.getBalanceByIbanAndCurrency(json.get("ibanSender"), json.get("currency"));
        Balance ibanReceiver = balanceService.getBalanceByIbanAndCurrency(json.get("ibanReceiver"), json.get("currency"));

        Transaction transaction = new Transaction(ibanReceiver, ibanSender, json.get("operType"), json.get("currency"), amount, dateTime, weekend, status);

        if (weekend == 1 || dateTime.isAfter(LocalDateTime.now())){
            transactionService.saveTransaction(transaction);
        }
        transactionService.performTransaction(transaction);
    }
}
