package com.example.Api.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
