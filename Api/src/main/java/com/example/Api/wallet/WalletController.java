package com.example.Api.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/wallets")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @GetMapping
    public List<Wallet> getWallets(){
        return walletService.getWallets();
    }

    @PostMapping
    public void registerNewWallet(@RequestBody Wallet wallet){

        walletService.addNewWallet(wallet);
    }

    @DeleteMapping(path = "{walletId}")
    public void deleteWallet(@PathVariable("walletId") Long walletID){
        walletService.deleteWallet(walletID);
    }

    @PutMapping(path = "{walletId}")
    public void updateWallet(
            @PathVariable("walletId") Long walletID,
            @RequestParam(required = false) Integer activity
    ){
        walletService.updateWallet(walletID, activity);
    }
}
