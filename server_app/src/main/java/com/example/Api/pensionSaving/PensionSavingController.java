package com.example.Api.pensionSaving;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.util.Map;


/**
 * PensionSaving controller to catch HTTP requests from the client
 * to the API
 */
@RestController
@RequestMapping("api/v1/pensionSavings")
public class PensionSavingController {

    private final PensionSavingService pensionSavingService;

    /**
     * Constructor of the class
     * @param pensionSavingService The PensionSavingService
     */
    @Autowired
    public PensionSavingController(PensionSavingService pensionSavingService){
        this.pensionSavingService = pensionSavingService;
    }

    /**
     * Method to catch a pension saving GET request by its ID
     * @param pensionID The id of the pension saving we want to get
     * @return Pension Saving data
     */
    @GetMapping("{pensionID}")
    public PensionSaving getByID(Long pensionID){
        return pensionSavingService.getPensionSavingByID(pensionID);
    }

    /**
     * Method to catch a Pension Saving POST request
     * @param json The json sent by the client
     */
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
