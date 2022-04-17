package com.example.Api.insurance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/insurances")
public class InsuranceController {

    private final InsuranceService insuranceService;


    @Autowired
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping(path = "{walletID}")
    public List<Insurance> getAllByWalletID(@PathVariable("walletID") Long walletID){
        return insuranceService.getAllByWalletID(walletID);
    }

    @GetMapping(path = "{bic}/{userID}")
    public List<Insurance> getAllByClient(@PathVariable("bic") String bic, @PathVariable("userID") String userID){
        return insuranceService.getAllByClient(bic, userID);
    }

    //get also inactive one
    @GetMapping(path = "all/{walletID}")
    public List<Insurance> getAllAndInactive(@PathVariable("walletID") Long walletID){
        return insuranceService.getAllWithInactive(walletID);
    }

}
