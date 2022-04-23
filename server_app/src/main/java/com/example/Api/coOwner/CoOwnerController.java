package com.example.Api.coOwner;

import com.example.Api.wallet.Wallet;
import com.example.Api.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * CoOwner controller
 */
@RestController
@RequestMapping(path = "api/v1/coOwners")
public class CoOwnerController {

    private final CoOwnerService coOwnerService;
    private final WalletService walletService;

    /**
     * Constructor
     * @param coOwnerService CoOwner Service
     * @param walletService Wallet Service
     */
    @Autowired
    public CoOwnerController(CoOwnerService coOwnerService, WalletService walletService){
        this.coOwnerService = coOwnerService;
        this.walletService = walletService;
    }

    /**
     * Method to catch a POST request for a coOwner
     * @param json The json data sent by the client
     */
    @PostMapping
    public void createCoOwner(@RequestBody Map<String, String> json){
        //Parse the walletID
        Long walletID = Long.parseLong(json.get("walletID_coOwner"));

        //Create a wallet instance calling the wallet service
        Wallet wallet = walletService.getWalletByWalletID(walletID).get();

        //calling coOwner service to register the co owner
        coOwnerService.registerCoOwner(wallet.getWalletID(), json.get("ibanOwner"), json.get(""), json.get("bicOwner"), json.get("userIDOwner"));
    }

    /**
     * Method to catch a GET request asking for all the coOwner of an Account
     * @param ibanOwner the iban of the owner Account
     * @return a List containing all the coOwners
     */
    @GetMapping("{ibanOwner}")
    public List<CoOwner> getAllByIbanOwner(@PathVariable("ibanOwner") String ibanOwner){
        return coOwnerService.getAllByIbanOwner(ibanOwner);
    }

    /**
     * Method to catch a DELETE request to delete a coOwner of an Account
     * @param walletID_coOwner the walletID of the coOwner
     * @param ibanOwner the iban of the owner account
     */
    @DeleteMapping(path = "{walletID_coOwner}/{ibanOwner}")
    public void deleteCoOwner(@PathVariable("walletID_coOwner") Long walletID_coOwner, @PathVariable("ibanOwner") String ibanOwner){
        coOwnerService.deleteCoOwner(walletID_coOwner, ibanOwner);
    }
}
