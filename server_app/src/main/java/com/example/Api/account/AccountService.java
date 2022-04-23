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

/**
 * Account Service
 */
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final BankRepository bankRepository;
    private final BalanceService balanceService;
    private final BankIdentifierService bankIdentifierService;
    private final WalletService walletService;
    private final BalanceRepository balanceRepository;

    /**
     * Constructor
     * @param accountRepository Account Repository
     * @param clientRepository Client Repository
     * @param bankRepository Bank Repository
     * @param balanceService Balance Service
     * @param bankIdentifierService Bank Identifier Service
     * @param walletService Wallet Service
     * @param balanceRepository Balance Repository
     */
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

    /**
     * Method to get all the accounts of a wallet calling the repository
     * @param walletID the walletID of the Wallet we want the accounts from
     * @return a List of all the Accounts of the Wallet
     */
    public List<Account> getAccounts(Long walletID){
        return accountRepository.findAccountByWalletID(walletID);
    }

    /**
     * Method to get all the Accounts of a Client calling the repository
     * @param bic the bic of the institution the client is registered in
     * @param userID the userID of the client
     * @return A List of all the Accounts of the Client
     */
    public List<Account> getAccountsForClient(String bic, String userID){
        Optional<Bank> bankOptional = bankRepository.findBankByBic(bic);
        Optional<Client> client = clientRepository.findByBankAndUserID(bic, userID);

        //throw an exception if the bank or the client does not exist
        if(bankOptional.isEmpty() || client.isEmpty()){
            throw new ApiNotFoundException("Bank or client not found");
        }

        //else return the accounts
        return accountRepository.findAccountByClient(client.get());
    }

    /**
     * Method to get an Account by its iban
     * @param iban the iban of the Account
     * @return the Account if found, throw an exception else
     * @throws ApiNotFoundException
     */
    public Account getIbanAccount(String iban) throws ApiNotFoundException{
        Optional<Account> accountOptional = accountRepository.findByIban(iban);

        //if the account does not exist throw an exception
        if(accountOptional.isEmpty()){
            throw new ApiNotFoundException("This account : "+iban+" doesn't exist");
        }

        //else return the account
        return accountOptional.get();
    }

    /**
     * Method to get all the Account of a Bank
     * @param bic the bic of the bank
     * @return List containing all the accounts of the bank
     */
    public List<Account> findAllAccountForBank(String bic){
        return accountRepository.findAccountsByBic(bic);
    }

    /**
     * Method to register a new Account for a client
     * @param client The client who wants to create a new account
     * @param type the type of account the client wants
     * @return true if the account is created, false else
     */
    public boolean registerAccount(Client client, String type){
        //calling the generator to create a new iban
        String iban = ibanGenerator(client.getClientIDEmb().getBic());

        //find the wallet of the client in the institution
        Wallet wallet = walletService.getWalletByUserAndBic(client.getClientIDEmb().getBic(), client.getClientIDEmb().getUserID());

        //create the new account
        Account account = new Account(iban, wallet, client, type, 0f, "EUR", 1);

        //saving it
        accountRepository.save(account);

        //An account must have at least 1 balance in EUR (to perform transaction etc) so we create it also.
        balanceService.registerBalance(iban, "EUR", 0f);

        //Check if the account and balance are created
        return accountRepository.findByIban(iban).isPresent() && balanceRepository.findByIbanAndCurrency(iban, "EUR").isPresent();
    }

    /**
     * Method to generate a new iban for an Account
     * @param bic the bic of the institution that has to generate the iban
     * @return a String corresponding to the iban
     */
    public String ibanGenerator(String bic){
        //First get the identifiers of the bank
        BankIdentifier bankIdentifier = bankIdentifierService.getByBic(bic);
        //Set the 7 first char of the iban
        String iban = bankIdentifier.getPrefix() + bankIdentifier.getIdentifier();
        //Get the new iban by incrementing the latest given by 1
        Integer newLast = bankIdentifier.getLastIban() + 1;
        //fill the missing char with 0
        int missingLength = 9 - newLast.toString().length();


        int i = 0;
        while (i < missingLength){
            iban += "0";
            i++;
        }
        iban += newLast;

        //update the bank identifier
        bankIdentifierService.updateLastIban(bic, newLast);

        return iban;
    }

    /**
     * Method to update the activity of an account
     * @param iban the iban of the account
     * @param activity the new activity
     */
    @Transactional
    public void updateAccount(String iban, Integer activity){
        //Chcek if account exists and if not throw an exception
        Account account = accountRepository.findByIbanToModify(iban).orElseThrow(
                () -> new ApiNotFoundException("Account with iban : " + iban + " doesn't exist")
        );

        //If the activty is already set to this state throw an exception
        if(activity.equals(account.getActivity())){
            throw new ApiIncorrectException("Activity is already set to : " + activity);
        }
        //else set it
        account.setActivity(activity);
    }
}
