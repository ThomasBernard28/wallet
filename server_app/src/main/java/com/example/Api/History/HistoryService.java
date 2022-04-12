package com.example.Api.History;

import com.example.Api.balance.Balance;
import com.example.Api.balance.BalanceRepository;
import com.example.Api.exception.ApiIncorrectException;
import com.example.Api.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final BalanceRepository balanceRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository, BalanceRepository balanceRepository){
        this.historyRepository = historyRepository;
        this.balanceRepository = balanceRepository;
    }

    public void separateTrxHistory(Transaction transaction){
        if (transaction.getStatus() == 0){
            throw new ApiIncorrectException("The transaction is not finished");
        }
        Balance balanceIDSender = balanceRepository.findByIbanAndCurrency(transaction.getIbanSender().getIban().getIban(), transaction.getCurrency()).get();
        Balance balanceIDReceiver = balanceRepository.findByIbanAndCurrency(transaction.getIbanReceiver().getIban().getIban(), transaction.getCurrency()).get();

        saveSenderHistory(transaction, balanceIDSender);
        saveReceiverHistory(transaction, balanceIDReceiver);
        System.out.println("second transaction sent");
    }

    public void saveSenderHistory(Transaction transaction, Balance balanceIDViewer){

        Balance balance = balanceIDViewer;
        //Because the balance has already been updated in the TransactionService
        Float prevBalance = balance.getBalance() + transaction.getAmount();
        Float amount = -transaction.getAmount();
        Float nextBalance = balance.getBalance();

        History history = new History(new HistoryIDEmb(transaction.getTrxID(), balance.getBalanceID()), balance, transaction.getIbanReceiver(),
                     amount, prevBalance, nextBalance, transaction.getDateTime(), transaction.getComments());

        historyRepository.save(history);
    }

    public void saveReceiverHistory(Transaction transaction, Balance balanceIDViewer){

        Balance balance = balanceIDViewer;
        Float prevBalance = balance.getBalance() - transaction.getAmount();
        Float amount = transaction.getAmount();
        Float nextBalance = balance.getBalance();

        History history = new History(new HistoryIDEmb(transaction.getTrxID(), balance.getBalanceID()), transaction.getIbanSender(), transaction.getIbanReceiver(),
                amount, prevBalance, nextBalance, transaction.getDateTime(), transaction.getComments());

        historyRepository.save(history);

    }
}
