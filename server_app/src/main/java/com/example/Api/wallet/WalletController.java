package com.example.Api.wallet;


import com.example.Api.exception.ApiNotFoundException;
import com.example.Api.user.User;
import com.example.Api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Wallet Controller working on the same principle as the User's one
 */
@RestController
@RequestMapping(path = "api/v1/wallets")
public class WalletController {

    private final WalletService walletService;
    private final UserService userService;

    @Autowired
    public WalletController(WalletService walletService, UserService userService){
        this.walletService = walletService;
        this.userService = userService;
    }

    @GetMapping
    public List<Wallet> getWallets(){
        return walletService.getWallets();
    }

    @GetMapping(path = "{userId}")
    public List<Wallet> getUserWallets(@PathVariable("userId") String userID){
        List<Wallet> wallets = walletService.getUserWallets(userID);
        List<Wallet> activeWallets = new ArrayList<>();

        for (Wallet wallet: wallets) {
            if (wallet.getActivity().equals(1)){
                activeWallets.add(wallet);
            }
        }
        return activeWallets;
    }

    @PostMapping
    public void registerNewWallet(@RequestBody Map<String, String> json){
        //We need to parse the data to their original types
        Integer activity = Integer.parseInt(json.get("activity"));
        LocalDate localDate = LocalDate.parse(json.get("openingDate"));

        Optional<User> user = userService.getOneUser(json.get("userID"));

        if(user.isPresent()){
            walletService.addNewWallet(json.get("userID"), json.get("bic"), localDate, activity);
        }
        else{
            //We need an existing user to register a wallet
            throw new ApiNotFoundException("User : " + json.get("userID") + " not found");
        }
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
