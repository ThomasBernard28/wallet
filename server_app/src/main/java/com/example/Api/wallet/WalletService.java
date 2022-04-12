package com.example.Api.wallet;


import com.example.Api.exception.ApiIncorrectException;
import com.example.Api.exception.ApiNotFoundException;
import com.example.Api.user.User;
import com.example.Api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository, UserRepository userRepository){
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    public List<Wallet> getWallets(){
        return walletRepository.findAll();
    }

    public List<Wallet> getUserWallets(String userID){
        Optional<User> user = userRepository.findUserByUserID(userID);

        if(!user.isPresent()){
            throw new ApiNotFoundException("The user : "+ userID +" doesn't exist");
        }
        return walletRepository.findWalletByUserEquals(user.get());
    }

    public Optional<Wallet> getWalletByWalletID(Long walletID){
        Optional<Wallet> walletOptional = walletRepository.findWalletByWalletID(walletID);

        if(!walletOptional.isPresent()){
            throw new ApiNotFoundException("Wallet with id : " + walletID +" doesn't exist");
        }
        if(walletOptional.get().getActivity().equals(0)){
            throw new ApiIncorrectException("The wallet with id : " + walletID + " is currently inactive ");
        }
        return walletOptional;
    }

    public void addNewWallet(String userID, String bic, LocalDate openingDate, Integer activity) {
        Wallet wallet = new Wallet(userRepository.getById(userID), bic, openingDate, activity);
        Optional<Wallet> walletOptional = walletRepository.findWalletByWalletID(wallet.getWalletID());

        if(walletOptional.isPresent()){
            throw new ApiNotFoundException("Wallet for user : "+ userID + "in the insitution : "+ bic + " already exists");
        }

        Optional<Wallet> walletForUser = walletRepository.findWalletByUserAndBic(wallet.getUser().getUserID(), wallet.getBic());

        if (walletForUser.isPresent()){
            throw new ApiIncorrectException("User already has a wallet in this institution : "+ bic);
        }
        walletRepository.save(wallet);

    }

    public void deleteWallet(Long walletID){
        boolean exists = walletRepository.existsById(walletID);

        if(!exists){
            throw new ApiNotFoundException("The wallet with id : "+ walletID+" doesn't exist");
        }
        walletRepository.deleteById(walletID);
    }

    @Transactional
    public void updateWallet(Long walletID, Integer activity){
        Wallet wallet = walletRepository.findWalletByWalletID(walletID).orElseThrow(
                () -> new ApiNotFoundException("Wallet with id " + walletID + "doesn't exist")
        );

        if (activity.equals(wallet.getActivity())){
            throw new ApiIncorrectException("The activity of the wallet is already set to" + activity);
        }
        wallet.setActivity(activity);
    }
}