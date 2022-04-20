package com.example.Api.wallet;


import com.example.Api.client.Client;
import com.example.Api.client.ClientService;
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
    private final ClientService clientService;

    @Autowired
    public WalletController(WalletService walletService, UserService userService, ClientService clientService){
        this.walletService = walletService;
        this.userService = userService;
        this.clientService = clientService;
    }

    @GetMapping
    public List<Wallet> getWallets(){
        return walletService.getWallets();
    }

    @GetMapping(path = "{userId}")
    public List<Wallet> getUserWallets(@PathVariable("userId") String userID){
        return walletService.getUserWallets(userID);

    }

    @GetMapping(path = "all/{userID}")
    public List<Wallet> getAllUserWallets(@PathVariable("userID") String userID){
        return walletService.getAllUserWallets(userID);
    }

    @PostMapping
    public void registerNewWallet(@RequestBody Map<String, String> json){
        //We need to parse the data to their original types
        Integer activity = Integer.parseInt(json.get("activity"));
        LocalDate localDate = LocalDate.parse(json.get("openingDate"));

        User user = userService.getOneUser(json.get("userID"));

        Client client = clientService.getOneClient(json.get("bic"), json.get("userID"));

        walletService.addNewWallet(json.get("userID"), json.get("bic"), localDate, activity, client);
    }

    @DeleteMapping(path = "{walletId}")
    public void deleteWallet(@PathVariable("walletId") Long walletID){
        walletService.deleteWallet(walletID);
    }

    @PutMapping(path = "{walletId}")
    public void updateWallet(
            @PathVariable("walletId") Long walletID,
            @RequestBody Map<String, String> json
    ){
        walletService.updateWallet(walletID, Integer.parseInt(json.get("activity")));
    }
}
