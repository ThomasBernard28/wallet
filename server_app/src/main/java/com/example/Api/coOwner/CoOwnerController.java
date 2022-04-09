package com.example.Api.coOwner;

import com.example.Api.wallet.Wallet;
import com.example.Api.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/coOwners")
public class CoOwnerController {

    private final CoOwnerService coOwnerService;
    private final WalletService walletService;

    @Autowired
    public CoOwnerController(CoOwnerService coOwnerService, WalletService walletService){
        this.coOwnerService = coOwnerService;
        this.walletService = walletService;
    }

    @PostMapping
    public void createCoOwner(@RequestBody Map<String, String> json){
        Long walletID = Long.parseLong(json.get("walletID_coOwner"));

        Wallet wallet = walletService.getWalletByWalletID(walletID).get();

        try{
            coOwnerService.registerCoOwner(wallet.getWalletID(), json.get("ibanOwner"), wallet.getUser().getUserID(),
                    json.get("bicOwner"), json.get("userIDOwner"));
        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }
    }
}
