package com.example.Api.pensionSaving;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.util.Map;

@RestController
@RequestMapping("api/v1/pensionSavings")
public class PensionSavingController {

    private final PensionSavingService pensionSavingService;

    @Autowired
    public PensionSavingController(PensionSavingService pensionSavingService){
        this.pensionSavingService = pensionSavingService;
    }

    @PostMapping
    public void registerPensionSaving(@RequestBody Map<String, String> json){
        Long walletID = Long.parseLong(json.get("walletID"));
        Float percentage = 30f;
        LocalDate subDate = LocalDate.parse(json.get("subDate"));
        LocalDate renewDate = subDate.plusYears(1);
        Float balance = Float.parseFloat(json.get("balance"));

        pensionSavingService.createPensionSaving(walletID, json.get("userID"), json.get("bic"), percentage, subDate, renewDate, json.get("type"), balance, 1);
    }
}
