package com.example.Api.account;

import com.example.Api.balance.BalanceRepository;
import com.example.Api.balance.BalanceService;
import com.example.Api.bank.Bank;
import com.example.Api.bank.BankRepository;
import com.example.Api.bankIdentifiers.BankIdentifier;
import com.example.Api.bankIdentifiers.BankIdentifierService;
import com.example.Api.client.Client;

import com.example.Api.client.ClientRepository;

import com.example.Api.exception.ApiIncorrectException;
import com.example.Api.exception.ApiNotFoundException;
import com.example.Api.wallet.Wallet;
import com.example.Api.wallet.WalletService;
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
    private final BankIdentifierService bankIdentifierService;
    private final WalletService walletService;
    private final BalanceRepository balanceRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, ClientRepository clientRepository,
                          BankRepository bankRepository, BalanceService balanceService,
                          BankIdentifierService bankIdentifierService, WalletService walletService, BalanceRepository balanceRepository){
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.bankRepository = bankRepository;
        this.balanceService = balanceService;
        this.bankIdentifierService = bankIdentifierService;
        this.walletService = walletService;
        this.balanceRepository = balanceRepository;
    }

    public List<Account> getAccounts(Long walletID){
        return accountRepository.findAccountByWalletID(walletID);
    }

    public List<Account> getAccountsForClient(String bic, String userID){
        Optional<Bank> bankOptional = bankRepository.findBankByBic(bic);
        Optional<Client> client = clientRepository.findByBankAndUserID(bic, userID);

        if(bankOptional.isEmpty() || client.isEmpty()){
            throw new ApiNotFoundException("Bank or client not found");
        }

        return accountRepository.findAccountByClient(client.get());
    }

    public Account getIbanAccount(String iban) throws ApiNotFoundException{
        Optional<Account> accountOptional = accountRepository.findByIban(iban);

        if(accountOptional.isEmpty()){
            throw new ApiNotFoundException("This account : "+iban+" doesn't exist");
        }

        return accountOptional.get();
    }

    public List<Account> findAllAccountForBank(String bic){
        return accountRepository.findAccountsByBic(bic);
    }

    public boolean registerAccount(Client client, String type){
        String iban = ibanGenerator(client.getClientIDEmb().getBic());

        Wallet wallet = walletService.getWalletByUserAndBic(client.getClientIDEmb().getBic(), client.getClientIDEmb().getUserID());

        Account account = new Account(iban, wallet, client, type, 0f, "EUR", 1);

        accountRepository.save(account);

        balanceService.registerBalance(iban, "EUR", 0f);

        return accountRepository.findByIban(iban).isPresent() && balanceRepository.findByIbanAndCurrency(iban, "EUR").isPresent();
    }

    public String ibanGenerator(String bic){
        BankIdentifier bankIdentifier = bankIdentifierService.getByBic(bic);
        String iban = bankIdentifier.getPrefix() + bankIdentifier.getIdentifier();
        Integer newLast = bankIdentifier.getLastIban() + 1;
        int missingLength = 9 - newLast.toString().length();

        int i = 0;
        while (i < missingLength){
            iban += "0";
            i++;
        }
        iban += newLast;

        bankIdentifierService.updateLastIban(bic, newLast);

        return iban;
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
