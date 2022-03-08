package com.example.Api.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository){
        this.walletRepository = walletRepository;
    }

    public List<Wallet> getWallets(){
        return walletRepository.findAll();
    }

    public void addNewWallet(Wallet wallet){
        Optional<Wallet> walletOptional = walletRepository.findWalletByWalletID(wallet.getWalletID());

        if(walletOptional.isPresent()){
            throw new IllegalStateException("Wallet already exists");
        }
        Optional<Wallet> walletForUser = walletRepository.findWalletByUserIDAndBic(wallet.getUserID(), wallet.getBic());

        if (walletForUser.isPresent()){
            throw new IllegalStateException("User already has a wallet in this institution");
        }

        walletRepository.save(wallet);
    }
    public void deleteWallet(Long walletID){
        boolean exists = walletRepository.existsById(walletID);

        if(!exists){
            throw new IllegalStateException("The wallet doesn't exist");
        }

        walletRepository.deleteById(walletID);
    }

    @Transactional
    public void updateWallet(Long walletID, Integer activity){
        Wallet wallet = walletRepository.findWalletByWalletID(walletID).orElseThrow(
                () -> new IllegalStateException("Wallet with id " + walletID + "doesn't exist")
        );

        if (activity.equals(wallet.getActivity())){
            throw new IllegalStateException("The activity of the wallet is already set to" + activity);
        }

        else{
            wallet.setActivity(activity);
        }

    }
}
