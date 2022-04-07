package com.example.Api.balance;

import com.example.Api.account.Account;
import com.example.Api.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public BalanceService(BalanceRepository balanceRepository, AccountRepository accountRepository){
        this.balanceRepository = balanceRepository;
        this.accountRepository = accountRepository;
    }

    public List<Balance> getBalancesByIban(String iban){
        return balanceRepository.findByIban(iban);
    }

    public Balance getBalanceById(Long balanceID){
        Optional<Balance> optionalBalance = balanceRepository.findByBalanceID(balanceID);

        if(!optionalBalance.isPresent()){
            throw new EntityNotFoundException("The blance with the ID : " + balanceID + " doesn't exist");
        }

        return optionalBalance.get();
    }

    public void registerBalance(String iban, String currency, Float balance) throws IllegalStateException{
        Optional<Balance> optionalBalance = balanceRepository.findByIbanAndCurrency(iban, currency);

        if (optionalBalance.isPresent()){
            throw new IllegalStateException("The balance already exists");
        }

        Optional<Account> account = accountRepository.findByIban(iban);

        if(!account.isPresent()){
            throw new EntityNotFoundException("Account with iban : " + iban + " doesn't exist");
        }

        Balance newBalance = new Balance(account.get(), currency, balance);
        balanceRepository.save(newBalance);
    }
}
