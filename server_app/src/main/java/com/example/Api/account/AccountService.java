package com.example.Api.account;

import com.example.Api.bank.Bank;
import com.example.Api.bank.BankRepository;
import com.example.Api.client.Client;

import com.example.Api.client.ClientRepository;

import com.example.Api.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final BankRepository bankRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, ClientRepository clientRepository, BankRepository bankRepository){
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.bankRepository = bankRepository;
    }

    public List<Account> getAccounts(Long walletID){
        return accountRepository.findAccountByWalletID(walletID);
    }

    public List<Account> getAccountsForClient(String bic, String userID){
        Optional<Bank> bankOptional = bankRepository.findBankByBic(bic);
        Optional<Client> client = clientRepository.findByBankAndUserID(bic, userID);

        if(!bankOptional.isPresent() ||  !client.isPresent()){
            throw new EntityNotFoundException("Bank or client not found");
        }

        return accountRepository.findAccountByClient(client.get());
    }

    public Account getIbanAccount(String iban) throws EntityNotFoundException{
        Optional<Account> accountOptional = accountRepository.findByIban(iban);

        if(!accountOptional.isPresent()){
            throw new EntityNotFoundException("This account doesn't exist");
        }

        return accountOptional.get();
    }

    public void registerAccount(String iban, Wallet wallet, Client client, String type, Float avgBalance, String localCurr, Integer activity ){
        Account account = new Account(iban, wallet, client, type, avgBalance, localCurr, activity);
        accountRepository.save(account);
    }
    @Transactional
    public void updateAccount(String iban, Integer activity, Float avgBalance){
        Account account = accountRepository.findByIban(iban).orElseThrow(
                () -> new EntityNotFoundException("Account with iban : " + iban + " doesn't exist")
        );

        if(activity.equals(account.getActivity())){
            throw new IllegalStateException("Activity is already set to : " + activity);
        }
        account.setActivity(activity);
        account.setAvgBalance(avgBalance);
    }
}
