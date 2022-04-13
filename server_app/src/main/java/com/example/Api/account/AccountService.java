package com.example.Api.account;

import com.example.Api.balance.BalanceService;
import com.example.Api.bank.Bank;
import com.example.Api.bank.BankRepository;
import com.example.Api.client.Client;

import com.example.Api.client.ClientRepository;

import com.example.Api.exception.ApiIncorrectException;
import com.example.Api.exception.ApiNotFoundException;
import com.example.Api.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final BankRepository bankRepository;
    private final BalanceService balanceService;

    @Autowired
    public AccountService(AccountRepository accountRepository, ClientRepository clientRepository,
                          BankRepository bankRepository, BalanceService balanceService){
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.bankRepository = bankRepository;
        this.balanceService = balanceService;
    }

    public List<Account> getAccounts(Long walletID){
        return accountRepository.findAccountByWalletID(walletID);
    }

    public List<Account> getAccountsForClient(String bic, String userID){
        Optional<Bank> bankOptional = bankRepository.findBankByBic(bic);
        Optional<Client> client = clientRepository.findByBankAndUserID(bic, userID);

        if(!bankOptional.isPresent() ||  !client.isPresent()){
            throw new ApiNotFoundException("Bank or client not found");
        }

        return accountRepository.findAccountByClient(client.get());
    }

    public Account getIbanAccount(String iban) throws ApiNotFoundException{
        Optional<Account> accountOptional = accountRepository.findByIban(iban);

        if(!accountOptional.isPresent()){
            throw new ApiNotFoundException("This account : "+iban+" doesn't exist");
        }

        return accountOptional.get();
    }

    public void registerAccount(String iban, Wallet wallet, Client client, String type, Float avgBalance, String localCurr, Integer activity ){
        Account account = new Account(iban, wallet, client, type, avgBalance, localCurr, activity);
        accountRepository.save(account);

        try {
            balanceService.registerBalance(iban, localCurr, avgBalance);
        }catch (ApiIncorrectException e){
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    public void updateAccount(String iban, Integer activity){
        Account account = accountRepository.findByIbanToModify(iban).orElseThrow(
                () -> new ApiNotFoundException("Account with iban : " + iban + " doesn't exist")
        );

        if(activity.equals(account.getActivity())){
            throw new ApiIncorrectException("Activity is already set to : " + activity);
        }
        account.setActivity(activity);
    }
}
