package com.example.Api.transaction;

import com.example.Api.balance.Balance;
import com.example.Api.balance.BalanceService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.xpath.XPath;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping(path = "{trxID}")
    public Transaction getTrxById(Long trxID){
        return transactionService.getTrxById(trxID);
    }

    @GetMapping(path = "date/{dateTime}")
    public List<Transaction> getTrxByDate(@PathVariable("dateTime") LocalDateTime dateTime){
        return transactionService.getTransactionToProcess(dateTime);
    }

    @GetMapping(path = "localDate")
    public LocalDate getServerDate(){
        return LocalDate.now();
    }

    @PostMapping
    public void createTransaction(@RequestBody Map<String, String> json){
        LocalDateTime dateTime = LocalDateTime.parse(json.get("dateTime"));
        Integer status = Integer.parseInt(json.get("status"));
        Integer weekend = Integer.parseInt(json.get("weekend"));
        Float amount = Float.parseFloat(json.get("amount"));
        Balance ibanSender = balanceService.getBalanceByIbanAndCurrency(json.get("ibanSender"), json.get("currency"));
        Balance ibanReceiver = balanceService.getBalanceByIbanAndCurrency(json.get("ibanReceiver"), json.get("currency"));

        Transaction transaction = new Transaction(ibanReceiver.getIban().getIban(), ibanSender.getIban().getIban(), json.get("operType"), json.get("currency"), amount, dateTime, weekend, status, json.get("comments"));

        //If the transaction is planed for the future or it's a weekend day
        // we don't execute it we only save it. The scheduler will process it on the right moment
        if (dateTime.isAfter(LocalDateTime.now()) || weekend == 1){
            transactionService.saveTransaction(transaction);
        }
        else{
            transactionService.performTransaction(transaction);
        }
    }
}
