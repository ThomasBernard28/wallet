package com.example.Api.coOwner;

import com.example.Api.wallet.Wallet;
import com.example.Api.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        coOwnerService.registerCoOwner(wallet.getWalletID(), json.get("ibanOwner"), wallet.getUser().getUserID(), json.get("bicOwner"), json.get("userIDOwner"));

    }

    @GetMapping("{ibanOwner}")
    public List<CoOwner> getAllByIbanOwner(@PathVariable("ibanOwner") String ibanOwner){
        return coOwnerService.getAllByIbanOwner(ibanOwner);
    }

    @DeleteMapping(path = "{walletID_coOwner}/{ibanOwner}")
    public void deleteCoOwner(@PathVariable("walletID_coOwner") Long walletID_coOwner, @PathVariable("ibanOwner") String ibanOwner){
        coOwnerService.deleteCoOwner(walletID_coOwner, ibanOwner);
    }
}
