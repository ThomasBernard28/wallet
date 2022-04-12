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

    @Transactional
    public void performTransaction(Transaction transaction){

        Balance sender = transaction.getIbanSender();

        Balance receiver = transaction.getIbanReceiver();

        if(sender.getBalance() < transaction.getAmount()){
            throw new ApiIncorrectException("Transaction denied your balance : " + sender.getBalance() + " is insufficient for a transaction amount of : " + transaction.getAmount());
        }

        balanceService.updateBalance(sender, -transaction.getAmount());

        balanceService.updateBalance(receiver, transaction.getAmount());

        Account accountSender = transaction.getIbanSender().getIban();
        Account accountReceiver = transaction.getIbanReceiver().getIban();

        accountSender.setAvgBalance(sender.getBalance());
        accountReceiver.setAvgBalance(receiver.getBalance());

        transaction.setStatus(1);

        saveTransaction(transaction);

        historyService.separateTrxHistory(transaction);
    }

    public void saveTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }
}
