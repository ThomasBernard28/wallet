package com.example.Api.wallet;


import com.example.Api.account.Account;
import com.example.Api.account.AccountRepository;
import com.example.Api.client.Client;
import com.example.Api.exception.ApiIncorrectException;
import com.example.Api.exception.ApiNotFoundException;
import com.example.Api.user.User;
import com.example.Api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Wallet Service
 */
@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    /**
     * Constructor
     * @param walletRepository Wallet Repository
     * @param userRepository User Repository
     * @param accountRepository Account Repository
     */
    @Autowired
    public WalletService(WalletRepository walletRepository, UserRepository userRepository, AccountRepository accountRepository){
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Method to get all the wallets of the db
     * @return List containing all the wallets of the db
     */
    public List<Wallet> getWallets(){
        return walletRepository.findAll();
    }

    /**
     * Method to get all the wallet of a user
     * @param userID the userID of the User
     * @return List of the wallets of a User if found
     */
    public List<Wallet> getUserWallets(String userID){
        Optional<User> user = userRepository.findUserByUserID(userID);

        if(user.isEmpty()){
            throw new ApiNotFoundException("The user : "+ userID +" doesn't exist");
        }
        return walletRepository.findWalletByUserEquals(user.get().getUserID());
    }

    /**
     * Method to get a wallet of a client
     * @param bic the bic of the bank the client is registered to
     * @param userID the userID of the Client
     * @return the wallet if found, throw an exception else
     */
    public Wallet getWalletByUserAndBic(String bic, String userID){
        Optional<Wallet> optionalWallet = walletRepository.findWalletByUserAndBic(userID, bic);

        if (optionalWallet.isEmpty()){
            throw new ApiNotFoundException("Wallet for user : " +userID + " and bank : " + bic + " does not exist");
        }
        return optionalWallet.get();
    }

    /**
     * Method to get all User wallets including the inactives
     * @param userID the userID of the User
     * @return a List containing all the User wallets
     */
    public List<Wallet> getAllUserWallets(String userID){
        Optional<User> user = userRepository.findUserByUserID(userID);

        //If the user does not exist throw an exception
        if(user.isEmpty()){
            throw new ApiNotFoundException("The user : "+ userID +" doesn't exist");
        }
        return walletRepository.findWalletByUserEqualsAll(user.get().getUserID());
    }

    /**
     * Method to get a wallet by its walletID
     * @param walletID walletID of the Wallet
     * @return The wallet if found, throw an exception else
     */
    public Wallet getWalletByWalletID(Long walletID){
        Optional<Wallet> walletOptional = walletRepository.findWalletByWalletID(walletID);

        //If wallet does not exist throw an exception
        if(walletOptional.isEmpty()){
            throw new ApiNotFoundException("Wallet with id : " + walletID +" doesn't exist");
        }
        //if the activity of the wallet is set to 0 throw an exception
        if(walletOptional.get().getActivity().equals(0)){
            throw new ApiIncorrectException("The wallet with id : " + walletID + " is currently inactive ");
        }
        return walletOptional.get();
    }

    /**
     * Method to add a new Wallet
     * @param userID userID of the User creating a wallet
     * @param bic the bic of the bank in which the User wants to create its wallet
     * @param openingDate the date of creating
     * @param activity the activity (which will be set to 1)
     * @param client the Client(userID, bic) refering to the bank
     */
    public void addNewWallet(String userID, String bic, LocalDate openingDate, Integer activity, Client client) {
        Wallet wallet = new Wallet(userRepository.getById(userID), bic, openingDate, activity);
        Optional<Wallet> walletOptional = walletRepository.findWalletByWalletID(wallet.getWalletID());

        //if wallet already exists throw an exception
        if(walletOptional.isPresent()){
            throw new ApiNotFoundException("Wallet for user : "+ userID + "in the insitution : "+ bic + " already exists");
        }

        Optional<Wallet> walletForUser = walletRepository.findWalletByUserAndBic(wallet.getUser().getUserID(), wallet.getBic());

        //User can only have one wallet per Institution
        if (walletForUser.isPresent()){
            throw new ApiIncorrectException("User already has a wallet in this institution : "+ bic);
        }
        walletRepository.save(wallet);

        //Link all the account referring to that client to the wallet
        List<Account> accountsForTheWallet = accountRepository.findAccountByClient(client);

        for (Account account : accountsForTheWallet){
            accountRepository.addWalletToAccounts(wallet.getWalletID(), account.getIban());
        }

    }

    /**
     * Method to delete a wallet
     * @param walletID walletID of the Wallet to delete
     */
    public void deleteWallet(Long walletID){
        boolean exists = walletRepository.existsById(walletID);

        if(!exists){
            throw new ApiNotFoundException("The wallet with id : "+ walletID+" doesn't exist");
        }
        walletRepository.deleteById(walletID);
    }

    /**
     * Method to update the activity of a Wallet
     * @param walletID walletID of the Wallet to update
     * @param activity the activity to set
     */
    @Transactional
    public void updateWallet(Long walletID, Integer activity){
        //if wallet is not found throw an exception
        Wallet wallet = walletRepository.findWalletByWalletID(walletID).orElseThrow(
                () -> new ApiNotFoundException("Wallet with id " + walletID + "doesn't exist")
        );
        //If the activity is already set to the same state throw an exception
        if (activity.equals(wallet.getActivity())){
            throw new ApiIncorrectException("The activity of the wallet is already set to" + activity);
        }
        wallet.setActivity(activity);
    }
}
