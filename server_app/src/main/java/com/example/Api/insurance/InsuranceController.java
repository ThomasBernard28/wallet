package com.example.Api.insurance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Insurance Controller class
 */
@RestController
@RequestMapping(path = "api/v1/insurances")
public class InsuranceController {

    private final InsuranceService insuranceService;

    /**
     * Constructor
     * @param insuranceService Insurance Service providing method calls
     */
    @Autowired
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    /**
     * Method to catch a GET request asking for all the insurances of a wallet
     * @param walletID The walletID of the wallet we want the insurances from
     * @return A List of all insurances belonging to the wallet
     */
    @GetMapping(path = "{walletID}")
    public List<Insurance> getAllByWalletID(@PathVariable("walletID") Long walletID){
        return insuranceService.getAllByWalletID(walletID);
    }

    /**
     * Method to catch a GET request asking for all the insurances of Client(bic, userID)
     * @param bic The institution i  which the client is registered
     * @param userID The userID of the client
     * @return A List of all insurances belonging to the wallet
     */
    @GetMapping(path = "{bic}/{userID}")
    public List<Insurance> getAllByClient(@PathVariable("bic") String bic, @PathVariable("userID") String userID){
        return insuranceService.getAllByClient(bic, userID);
    }

    /**
     * Method to catch a GET request asking for all insurances of a Wallet including inactive ones
     * @param walletID The walletID of the Wallet we want the insurances from
     * @return A List of all insurances belonging to the wallet including the inactives
     */
    @GetMapping(path = "all/{walletID}")
    public List<Insurance> getAllAndInactive(@PathVariable("walletID") Long walletID){
        return insuranceService.getAllWithInactive(walletID);
    }

}
