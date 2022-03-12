package com.example.Api.wallet;

import com.example.Api.user.User;
import com.example.Api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        return walletService.getUserWallets(userID);
    }

    @PostMapping
    public void registerNewWallet(@RequestBody Map<String, String> json){
        System.out.println("Im here");
        Integer activity = Integer.parseInt(json.get("activity"));
        LocalDate localDate = LocalDate.parse(json.get("openingDate"));

        Optional<User> user = userService.getOneUser(json.get("userID"));

        if(user.isPresent()){
            System.out.println("User found");
            walletService.addNewWallet(json.get("userID"), json.get("bic"), localDate, activity);
        }
        else{
            throw new IllegalStateException("User not found");
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