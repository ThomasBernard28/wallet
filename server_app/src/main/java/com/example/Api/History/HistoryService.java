package com.example.Api.History;


import com.example.Api.balance.Balance;
import com.example.Api.balance.BalanceRepository;
import com.example.Api.exception.ApiIncorrectException;
import com.example.Api.exception.ApiNotFoundException;
import com.example.Api.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This is the History service which will convert transaction into an history for each account of a transaction.
 */
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

    /**
     * This method will get all the History of an account including all its balances including
     * multiple currencies. In the base App we only include EUR but with an extension we would be able
     * to manage multiple currencies accounts
     * @param iban The iban of the account we want the History from
     * @return A List containing all the TransactionHistory
     */
    public List<History> getAllByAccount(String iban){
        List<Balance> allBalances = balanceRepository.findByIban(iban);

        //If it's a single currency account
        if (allBalances.size() == 1){
            Balance balance = allBalances.get(0);
            return historyRepository.findByBalanceIdViewer(balance.getBalanceID());
        }
        //If it's a multiple currencies account
        else{
            //Set an empty List
            List<History> result = new ArrayList<>();
            int i = 0;
            //Loop for each Balance
            while(i < allBalances.size()){
                Balance balance = allBalances.get(i);
                //Temp list for each currency
                List<History> temp = historyRepository.findByBalanceIdViewer(balance.getBalanceID());
                //loop to add the records in the definitive one
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

    /**
     * This method works as the one with the iban but to only get the History before
     * a specific date
     * @param iban The iban from the account
     * @param dateTime The limit date
     * @return The List of Transaction
     */
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
    /**
     * This method works as the one with the iban but to only get the History after
     * a specific date
     * @param iban The iban from the account
     * @param dateTime The start date
     * @return The List of Transaction
     */
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

    /**
     * This method works as the one with the iban but to only get the History between
     * two specific dates
     * @param iban The iban from the account
     * @param startDateTime The start date
     * @param endDateTime The limit date
     * @return The List of Transaction
     */
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

    /**
     * This method separate a Transaction into two records to be saved for each account that was part of the transaction
     * @param transaction
     */
    public void separateTrxHistory(Transaction transaction){
        //We don't want to save a record for an unfinished transaction
        if (transaction.getStatus() == 0){
            throw new ApiIncorrectException("The transaction is not finished");
        }
        Balance balanceIDSender = balanceRepository.findByIbanAndCurrency(transaction.getIbanSender(), transaction.getCurrency()).get();
        System.out.println("balance id of sender : + " + balanceIDSender.getBalanceID());
        Balance balanceIDReceiver = balanceRepository.findByIbanAndCurrency(transaction.getIbanReceiver(), transaction.getCurrency()).get();
        System.out.println("balance id of receiver : " +balanceIDReceiver.getBalanceID());

        saveSenderHistory(transaction, balanceIDSender);
        saveReceiverHistory(transaction, balanceIDReceiver);
        System.out.println("sencond part sent");
    }

    public void saveSenderHistory(Transaction transaction, Balance balanceIDViewer){

        //Because the balance has already been updated in the TransactionService
        Float prevBalance = balanceIDViewer.getBalance() + transaction.getAmount();
        Float amount = -transaction.getAmount();
        Float nextBalance = balanceIDViewer.getBalance();

        historyRepository.insertHistory(transaction.getTrxID(), balanceIDViewer.getBalanceID(), amount,transaction.getDateTime(), nextBalance,
                prevBalance, transaction.getIbanReceiver(),  balanceIDViewer.getIban().getIban(), transaction.getComments());

        //System.out.println(history + "sender");
        //historyRepository.save(history);
    }

    public void saveReceiverHistory(Transaction transaction, Balance balanceIDViewer){

        //Because the balance has already been updated in the TransactionService
        Float prevBalance = balanceIDViewer.getBalance() - transaction.getAmount();
        Float amount = transaction.getAmount();
        Float nextBalance = balanceIDViewer.getBalance();

        historyRepository.insertHistory(transaction.getTrxID(), balanceIDViewer.getBalanceID(), amount,transaction.getDateTime(), nextBalance,
                prevBalance, transaction.getIbanReceiver(),  balanceIDViewer.getIban().getIban(), transaction.getComments());

        //System.out.println(history + "receiver");
        //historyRepository.save(history);

    }

    public void saveInsuranceHistory(Transaction transaction, Balance balanceIDViewer){
        Float prevBalance = balanceIDViewer.getBalance() - transaction.getAmount();
        Float amount  = transaction.getAmount();
        Float nextBalance = balanceIDViewer.getBalance();

        historyRepository.insertInsHistory(transaction.getTrxID(), balanceIDViewer.getBalanceID(), amount, transaction.getDateTime(), nextBalance,
                prevBalance,balanceIDViewer.getIban().getIban(), transaction.getInsIDReceiver());
    }
}

