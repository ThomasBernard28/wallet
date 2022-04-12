package com.example.Api.transaction;

import com.example.Api.History.HistoryService;
import com.example.Api.account.Account;
import com.example.Api.account.AccountRepository;
import com.example.Api.account.AccountService;
import com.example.Api.balance.Balance;
import com.example.Api.balance.BalanceRepository;
import com.example.Api.balance.BalanceService;
import com.example.Api.exception.ApiIncorrectException;
import com.example.Api.exception.ApiNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BalanceService balanceService;
    private final HistoryService historyService;


    @Autowired
    public TransactionService(TransactionRepository transactionRepository, BalanceService balanceService, HistoryService historyService){
        this.transactionRepository = transactionRepository;
        this.balanceService = balanceService;
        this.historyService = historyService;
    }

    public Transaction getTrxById(Long trxID){
        Optional<Transaction> transaction = transactionRepository.findByTrxID(trxID);

        if(transaction.isEmpty()){
            throw new ApiNotFoundException("No transaction with id : " + trxID + " wre found");
        }
        return transaction.get();
    }

    public List<Transaction> getTransactionToProcess(LocalDateTime dateTime){
        return transactionRepository.findByDateTime(dateTime);
    }

    /**
     * This method will perform a transaction updating both balances (Sender and Receiver)
     * When it's done it will send the transaction to the HistoryService to be stored in the history of the accounts
     * @param transaction The transaction to be performed
     */
    @Transactional
    public void performTransaction(Transaction transaction){

        Balance sender = balanceService.getBalanceByIbanAndCurrency(transaction.getIbanSender(), transaction.getCurrency());

        Balance receiver = balanceService.getBalanceByIbanAndCurrency(transaction.getIbanReceiver(), transaction.getCurrency());

        //Check if the balance of the sender has enough money
        if(sender.getBalance() < transaction.getAmount()){
            throw new ApiIncorrectException("Transaction denied your balance : " + sender.getBalance() + " is insufficient for a transaction amount of : " + transaction.getAmount());
        }

        balanceService.updateBalance(sender, -transaction.getAmount());

        balanceService.updateBalance(receiver, transaction.getAmount());

        Account accountSender = sender.getIban();
        Account accountReceiver = receiver.getIban();

        accountSender.setAvgBalance(sender.getBalance());
        accountReceiver.setAvgBalance(receiver.getBalance());

        //set the status to "done" so the scheduler won't do it again
        transaction.setStatus(1);
        //store the transaction as "done"
        saveTransaction(transaction);

        //Send the transaction to the history to be stored in each account history
        historyService.separateTrxHistory(transaction);
    }

    public void saveTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }
}
