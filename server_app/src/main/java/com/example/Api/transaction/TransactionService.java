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
import com.example.Api.penBalance.PenBalance;
import com.example.Api.penBalance.PenBalanceService;
import com.example.Api.pensionSaving.PensionSaving;
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
    private final PenBalanceService penBalanceService;

    /**
     * The TransactionService taking repositories and services in arguments
     * @param transactionRepository Transaction Repository
     * @param balanceService Balance Service
     * @param historyService History Service to store the processed transactions
     * @param penBalanceService Pension Balance Service
     */
    @Autowired
    public TransactionService(TransactionRepository transactionRepository, BalanceService balanceService, HistoryService historyService,
                              PenBalanceService penBalanceService){
        this.transactionRepository = transactionRepository;
        this.balanceService = balanceService;
        this.historyService = historyService;
        this.penBalanceService = penBalanceService;
    }

    /**
     * @param trxID The id of the transaction we want
     * @return The Transaction we want
     */
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

    /**
     * This method will perform a cash deposit updating the balance of the receiver
     * @param transaction The transaction to perform
     */
    public void performCashDeposit(Transaction transaction){
        Balance receiver = balanceService.getBalanceByIbanAndCurrency(transaction.getIbanReceiver(), transaction.getCurrency());

        balanceService.updateBalance(receiver, transaction.getAmount());

        Account accountReceiver = receiver.getIban();

        accountReceiver.setAvgBalance(receiver.getBalance());

        transaction.setStatus(1);

        saveTransaction(transaction);

        historyService.saveReceiverHistory(transaction, receiver);
    }

    /**
     * This method will perform a funding on a pension saving
     * @param transaction The transaction to perform
     */
    @Transactional
    public void performPenTrx(Transaction transaction){
        PenBalance penBalance = penBalanceService.findByPensionIdAndCurrency(transaction.getInsIDReceiver(), transaction.getCurrency());

        Balance ibanSender = balanceService.getBalanceByIbanAndCurrency(transaction.getIbanSender(), transaction.getCurrency());

        if (ibanSender.getBalance() < transaction.getAmount()){
            throw new ApiIncorrectException("Transaction denied your balance : " + ibanSender.getBalance() + " is insufficient for a transaction amount of : " + transaction.getAmount());
        }

        penBalanceService.updateBalance(penBalance, transaction.getAmount());

        balanceService.updateBalance(ibanSender, -transaction.getAmount());

        Account sender = ibanSender.getIban();

        PensionSaving pensionSaving = penBalance.getPensionSaving();

        sender.setAvgBalance(ibanSender.getBalance());

        pensionSaving.setBalance(penBalance.getBalance());

        transaction.setStatus(1);

        saveTransaction(transaction);

        historyService.saveInsuranceHistory(transaction, ibanSender);
    }

    public void saveTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }

}
