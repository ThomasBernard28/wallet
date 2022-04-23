package com.example.Api.wallet;


import com.example.Api.client.Client;
import com.example.Api.client.ClientService;
import com.example.Api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Wallet Controller working on the same principle as the User's one
 */
@RestController
@RequestMapping(path = "api/v1/wallets")
public class WalletController {

    private final WalletService walletService;
    private final UserService userService;
    private final ClientService clientService;

    /**
     * Constructor
     * @param walletService Wallet Service
     * @param userService User Service
     * @param clientService Client Service
     */
    @Autowired
    public WalletController(WalletService walletService, UserService userService, ClientService clientService){
        this.walletService = walletService;
        this.userService = userService;
        this.clientService = clientService;
    }

    /**
     * Method to catch a GET request asking for all the wallets of the db
     * @return a List containing all the wallets of the db
     */
    @GetMapping
    public List<Wallet> getWallets(){
        return walletService.getWallets();
    }

    /**
     * Method to catch a GET request asking for all the wallets of a User
     * @param userID the userID of the User we want the wallets from
     * @return A List containing all the wallets of the User
     */
    @GetMapping(path = "{userId}")
    public List<Wallet> getUserWallets(@PathVariable("userId") String userID){
        return walletService.getUserWallets(userID);

    }

    /**
     * Method to catch a GET request asking for all the wallets of a User including inactive ones
     * @param userID the userID of the User we want the wallets from
     * @return a List containing all the wallets of a User including inactive ones
     */
    @GetMapping(path = "all/{userID}")
    public List<Wallet> getAllUserWallets(@PathVariable("userID") String userID){
        return walletService.getAllUserWallets(userID);
    }

    /**
     * Method to catch a POST request asking to register a new Wallet
     * @param json the data sent by the client app
     */
    @PostMapping
    public void registerNewWallet(@RequestBody Map<String, String> json){
        //We need to parse the data to their original types
        Integer activity = Integer.parseInt(json.get("activity"));
        LocalDate localDate = LocalDate.parse(json.get("openingDate"));

        Client client = clientService.getOneClient(json.get("bic"), json.get("userID"));

        walletService.addNewWallet(json.get("userID"), json.get("bic"), localDate, activity, client);
    }

    /**
     * Method to delete a wallet (not used we use the PUT to set it to inactive)
     * @param walletID the walletID of the Wallet
     */
    @DeleteMapping(path = "{walletId}")
    public void deleteWallet(@PathVariable("walletId") Long walletID){
        walletService.deleteWallet(walletID);
    }

    /**
     * Method to catch a PUT request asking to set the wallet to Inactive
     * @param walletID the walletID of the Wallet
     * @param json the data sent by the json
     */
    @PutMapping(path = "{walletId}")
    public void updateWallet(
            @PathVariable("walletId") Long walletID,
            @RequestBody Map<String, String> json
    ){
        walletService.updateWallet(walletID, Integer.parseInt(json.get("activity")));
    }
}
