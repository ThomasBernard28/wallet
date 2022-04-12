package com.example.Api.History;

import com.example.Api.account.Account;
import com.example.Api.balance.Balance;
import com.example.Api.balance.BalanceRepository;
import com.example.Api.exception.ApiIncorrectException;
import com.example.Api.exception.ApiNotFoundException;
import com.example.Api.transaction.Transaction;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final BalanceRepository balanceRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository, BalanceRepository balanceRepository){
        this.historyRepository = historyRepository;
        this.balanceRepository = balanceRepository;
    }
    public List<History> findAll(){
        return historyRepository.findAll();
    }

    public List<History> getAllByAccount(String iban){
        List<Balance> allBalances = balanceRepository.findByIban(iban);

        if (allBalances.size() == 1){
            Balance balance = allBalances.get(0);
            return historyRepository.findByBalanceIdViewer(balance.getBalanceID());
        }
        else{
            List<History> result = new ArrayList<>();
            int i = 0;
            while(i < allBalances.size()){
                Balance balance = allBalances.get(i);
                List<History> temp = historyRepository.findByBalanceIdViewer(balance.getBalanceID());
                for(int j = 0; j < temp.size(); j++){
                    result.add(temp.get(j));
                }
                i++;
            }
            return result;
        }
    }

    public List<History> getAllByViewer(Long balanceIdViewer){
        Optional<Balance> balanceOptional = balanceRepository.findByBalanceID(balanceIdViewer);

        if(balanceOptional.isEmpty()){
            throw new ApiNotFoundException("The balance with id : " + balanceIdViewer + " does not exist");
        }
        return historyRepository.findByBalanceIdViewer(balanceIdViewer);
    }

    public List<History> findTrxBeforeDate(String iban, LocalDateTime dateTime){
        List<Balance> allBalances = balanceRepository.findByIban(iban);

        if (allBalances.size() == 1){
            Balance balance = allBalances.get(0);
            return historyRepository.findByBalanceIdViewerAndDateTimeBefore(balance.getBalanceID(), dateTime);
        }
        else{
            List<History> result = new ArrayList<>();
            int i = 0;
            while(i < allBalances.size()){
                Balance balance = allBalances.get(i);
                List<History> temp = historyRepository.findByBalanceIdViewerAndDateTimeBefore(balance.getBalanceID(), dateTime);
                for(int j = 0; j < temp.size(); j++){
                    result.add(temp.get(j));
                }
                i++;
            }
            return result;
        }
    }

    public List<History> findTrxAfterDate(String iban, LocalDateTime dateTime){
        List<Balance> allBalances = balanceRepository.findByIban(iban);

        if (allBalances.size() == 1){
            Balance balance = allBalances.get(0);
            return historyRepository.findByBalanceIdViewerAndDateTimeAfter(balance.getBalanceID(), dateTime);
        }
        else{
            List<History> result = new ArrayList<>();
            int i = 0;
            while(i < allBalances.size()){
                Balance balance = allBalances.get(i);
                List<History> temp = historyRepository.findByBalanceIdViewerAndDateTimeAfter(balance.getBalanceID(), dateTime);
                for(int j = 0; j < temp.size(); j++){
                    result.add(temp.get(j));
                }
                i++;
            }
            return result;
        }
    }

    public List<History> findTrxBetweenDates(String iban, LocalDateTime startDateTime, LocalDateTime endDateTime){
        List<Balance> allBalances = balanceRepository.findByIban(iban);

        if (allBalances.size() == 1){
            Balance balance = allBalances.get(0);
            return historyRepository.findByBalanceIdViewerAndDateTimeBetween(balance.getBalanceID(), startDateTime, endDateTime);
        }
        else{
            List<History> result = new ArrayList<>();
            int i = 0;
            while(i < allBalances.size()){
                Balance balance = allBalances.get(i);
                List<History> temp = historyRepository.findByBalanceIdViewerAndDateTimeBetween(balance.getBalanceID(), startDateTime, endDateTime);
                for(int j = 0; j < temp.size(); j++){
                    result.add(temp.get(j));
                }
                i++;
            }
            return result;
        }
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

        //Because the balance has already been updated in the TransactionService
        Float prevBalance = balanceIDViewer.getBalance() + transaction.getAmount();
        Float amount = -transaction.getAmount();
        Float nextBalance = balanceIDViewer.getBalance();

        History history = new History(transaction.getTrxID(), balanceIDViewer.getBalanceID(), balanceIDViewer.getIban().getIban(), transaction.getIbanReceiver().getIban().getIban(),
                     amount, prevBalance, nextBalance, transaction.getDateTime(), transaction.getComments());

        historyRepository.save(history);
    }

    public void saveReceiverHistory(Transaction transaction, Balance balanceIDViewer){

        Float prevBalance = balanceIDViewer.getBalance() - transaction.getAmount();
        Float amount = transaction.getAmount();
        Float nextBalance = balanceIDViewer.getBalance();

        History history = new History(transaction.getTrxID(), balanceIDViewer.getBalanceID(), transaction.getIbanSender().getIban().getIban(), transaction.getIbanReceiver().getIban().getIban(),
                amount, prevBalance, nextBalance, transaction.getDateTime(), transaction.getComments());

        historyRepository.save(history);

    }
}
