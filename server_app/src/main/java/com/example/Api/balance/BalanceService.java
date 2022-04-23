package com.example.Api.balance;

import com.example.Api.account.Account;
import com.example.Api.account.AccountRepository;
import com.example.Api.exception.ApiIncorrectException;
import com.example.Api.exception.ApiNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

/**
 * Balance Service
 */
@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final AccountRepository accountRepository;

    /**
     * Constructor
     * @param balanceRepository Balance Repository
     * @param accountRepository Account Repository
     */
    @Autowired
    public BalanceService(BalanceRepository balanceRepository, AccountRepository accountRepository){
        this.balanceRepository = balanceRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Method to get all balances of an account calling the balance repository
     * @param iban the iban of the account we want the balances from
     * @return A List containing all the balances
     */
    public List<Balance> getBalancesByIban(String iban){
        return balanceRepository.findByIban(iban);
    }

    /**
     * Method to get a balance by its ID
     * @param balanceID the balanceID of the balance we want
     * @return The balance if found, throw an exception else
     */
    public Balance getBalanceById(Long balanceID){
        Optional<Balance> optionalBalance = balanceRepository.findByBalanceID(balanceID);

        //If the balance does not exist throw an exception
        if(optionalBalance.isEmpty()){
            throw new ApiNotFoundException("The blance with the ID : " + balanceID + " doesn't exist");
        }
        //else return the balance
        return optionalBalance.get();
    }

    /**
     * Method to get a balance byt its iban and currency
     * @param iban the iban of the account the balance belongs to
     * @param currency the currency of the balance
     * @return The balance if found, throw an exception else
     */
    public Balance getBalanceByIbanAndCurrency(String iban, String currency){
        Optional<Balance> optionalBalance = balanceRepository.findByIbanAndCurrency(iban, currency);

        //If the balance does not exist throw an excpetion
        if(optionalBalance.isEmpty()){
            throw new ApiNotFoundException("No balance with iban : " + iban + " and currency : " + currency + " exists");
        }
        //else return the balance
        return optionalBalance.get();
    }

    /**
     * Method to register a new balance to an Account
     * @param iban the iban of the Account
     * @param currency the currency of the new balance
     * @param balance the total balance
     * @throws IllegalStateException
     */
    public void registerBalance(String iban, String currency, Float balance) throws IllegalStateException{
        Optional<Balance> optionalBalance = balanceRepository.findByIbanAndCurrency(iban, currency);

        //Check if the balance does not exist already. An Account can only have one balance of each currency. Throw an
        //exception if not the case
        if (optionalBalance.isPresent()){
            throw new ApiIncorrectException("The balance for the account : "+ iban+ " in "+ currency + " already exists");
        }
        Optional<Account> account = accountRepository.findByIban(iban);

        //If the account does not exist throw an exception
        if(account.isEmpty()){
            throw new ApiNotFoundException("Account with iban : " + iban + " doesn't exist");
        }
        //else create the balance and save it
        Balance newBalance = new Balance(account.get(), currency, balance);
        balanceRepository.save(newBalance);
    }

    /**
     * Method to update the balance of a Cash Balance
     * @param balance the previous balance
     * @param amount the amount
     */
    public void updateBalance(Balance balance, Float amount){
        balance.setBalance(balance.getBalance() + amount);
    }
}
