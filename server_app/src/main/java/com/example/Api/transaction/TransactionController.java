package com.example.Api.transaction;

import com.example.Api.balance.Balance;
import com.example.Api.balance.BalanceService;
import com.example.Api.penBalance.PenBalance;

import com.example.Api.penBalance.PenBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final BalanceService balanceService;
    private final PenBalanceService penBalanceService;

    /**
     * Constructor taking some Service classes in parameters
     * @param transactionService TransactionService class
     * @param balanceService BalanceService class
     * @param penBalanceService PenBalanceService class
     */
    @Autowired
    public TransactionController(TransactionService transactionService, BalanceService balanceService, PenBalanceService penBalanceService){
        this.transactionService = transactionService;
        this.balanceService = balanceService;
        this.penBalanceService = penBalanceService;
    }

    /**
     * Method to catch a GET request with trxID Path Variable
     * @param trxID The id of the transaction we want to get
     * @return The transaction with the id we gave
     */
    @GetMapping(path = "{trxID}")
    public Transaction getTrxById(Long trxID){
        return transactionService.getTrxById(trxID);
    }

    /**
     * Method used to catch a GET request with dateTime Path variable
     * @param dateTime The date and time we use to get the transactions to process
     * @return A list of transaction that have to be processed
     */
    @GetMapping(path = "date/{dateTime}")
    public List<Transaction> getTrxByDate(@PathVariable("dateTime") LocalDateTime dateTime){
        return transactionService.getTransactionToProcess(dateTime);
    }

    /**
     * This method return the date of the server in order to prevent date fraud
     * in the client application
     * @return the date of the server
     */
    @GetMapping(path = "localDate")
    public LocalDate getServerDate(){
        return LocalDate.now();
    }

    /**
     * Method to catch a transaction POST request
     * There is a checker in the method that checks if it's a future
     * transaction that doesn't have to be processed right now or if we are on a weekend.
     * If so the transaction will be stored in the database and executed by a scheduler.
     * Else the transaction is performed and the transaction is not stored only the history is.
     * @param json The json we receive from the client
     */
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

    /**
     * Method to catch a cash deposit POST request.
     * In this case the transaction only concern 1 account.
     * It simulates a Cash Deposit you could make from an ATM
     * @param json The json we receive from the client
     */
    @PostMapping(path = "cashDeposit")
    public void createCashDeposit(@RequestBody Map<String, String> json){
        LocalDateTime dateTime = LocalDateTime.parse(json.get("dateTime"));
        Integer status = Integer.parseInt(json.get("status"));
        Integer weekend = 0;
        Float amount = Float.parseFloat(json.get("amount"));
        Balance ibanReceiver = balanceService.getBalanceByIbanAndCurrency(json.get("ibanReceiver"), json.get("currency"));

        Transaction transaction = new Transaction(ibanReceiver.getIban().getIban(), json.get("operType"), json.get("currency"), amount, dateTime, weekend, status, json.get("comments"));

        transactionService.performCashDeposit(transaction);
    }

    /**
     * Method to catch a pension saving funding POST request.
     * In this case the transaction concern 1 account and 1 pension saving balance.
     * @param json The json we receive from the client
     */
    @PostMapping(path = "penFunding")
    public void createInsTrx(@RequestBody Map<String, String> json){
        LocalDateTime dateTime = LocalDateTime.parse(json.get("dateTime"));
        Integer status = Integer.parseInt(json.get("status"));
        Float amount = Float.parseFloat(json.get("amount"));

        //The pension saving balance we want to top up
        PenBalance penBalance = penBalanceService.findByPensionIdAndCurrency(Long.parseLong(json.get("insID")), "EUR");

        //The balance of the account we want the money from
        Balance ibanSender = balanceService.getBalanceByIbanAndCurrency(json.get("ibanSender"), "EUR");

        Transaction transaction = new Transaction(ibanSender.getIban().getIban(), penBalance.getPensionSaving().getPensionID(),
                json.get("operType"), "EUR", amount, dateTime, status, json.get("comments"), Integer.parseInt(json.get("weekend")));

        if (dateTime.isAfter(LocalDateTime.now())){
            transactionService.saveTransaction(transaction);
        }
        else{
            transactionService.performPenTrx(transaction);
        }
    }
}
