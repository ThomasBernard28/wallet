package com.example.Api.account;

import com.example.Api.bank.Bank;
import com.example.Api.bank.BankRepository;
import com.example.Api.client.Client;

import com.example.Api.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        return accountRepository.findAccountByWallet(walletID);
    }

    public List<Account> getAccountsForClient(String bic, String userID){
        Optional<Bank> bankOptional = bankRepository.findBankByBic(bic);
        Optional<Client> client = clientRepository.findByBankAndUserID(bic, userID);

        if(!bankOptional.isPresent() ||  !client.isPresent()){
            throw new EntityNotFoundException("Bank or client not found");
        }

        return accountRepository.findAccountByClient(client.get());
    }
}
