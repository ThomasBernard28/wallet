package com.example.Api.penBalance;

import com.example.Api.exception.ApiIncorrectException;
import com.example.Api.exception.ApiNotFoundException;
import com.example.Api.insurance.Insurance;
import com.example.Api.insurance.InsuranceRepository;
import com.example.Api.insurance.InsuranceService;
import com.example.Api.pensionSaving.PensionSaving;
import com.example.Api.pensionSaving.PensionSavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Pension Balance Service
 */
@Service
public class PenBalanceService {

    private final PenBalanceRepository penBalanceRepository;
    private final PensionSavingRepository pensionSavingRepository;
    private final InsuranceRepository insuranceRepository;

    /**
     * Constructor
     * @param penBalanceRepository Pension Balance Repository
     * @param pensionSavingRepository Pension Saving Repository
     * @param insuranceRepository Insurance Repository
     */
    @Autowired
    public PenBalanceService(PenBalanceRepository penBalanceRepository, PensionSavingRepository pensionSavingRepository, InsuranceRepository insuranceRepository){
        this.penBalanceRepository = penBalanceRepository;
        this.pensionSavingRepository = pensionSavingRepository;
        this.insuranceRepository = insuranceRepository;
    }

    /**
     * @param pensionID The pension ID of the Pension Saving that contains this balance
     * @param currency The currency of the balance
     * @return The corresponding Pension Balance if found or throw an exception if not found
     */
    public PenBalance findByPensionIdAndCurrency(Long pensionID, String currency){
        //First we make sure that the Pension Saving exists
        if(pensionSavingRepository.findByPensionID(pensionID).isEmpty()){
            throw new ApiNotFoundException("The Pension Saving with id : " + pensionID + " doesn't exist");
        }
        Optional<PenBalance> optionalPenBalance = penBalanceRepository.findByPensionIDAndCurrency(pensionID, currency);

        //Then we make sure that the Pension Balance exists
        if(optionalPenBalance.isEmpty()){
            throw new ApiNotFoundException("Pension balance with id : " + pensionID + " and currency : " + currency + " does not exist");
        }
        return optionalPenBalance.get();
    }

    /**
     * @param penBalanceID The id of the balance we want to get
     * @return The Pension Balance if found or throw an exception if not
     */
    public PenBalance findByPenBalanceID(Long penBalanceID){
        Optional<PenBalance> optionalPenBalance = penBalanceRepository.findByPenBalanceID(penBalanceID);

        if(optionalPenBalance.isEmpty()){
            throw new ApiNotFoundException("Pension balance with id : " + penBalanceID + " does not exist");
        }

        return optionalPenBalance.get();
    }

    /**
     * @param pensionID The pensionID of the PensionSaving we want to add a PensionBalance to
     * @param balance The balance (default 0)
     * @param currency The currency (EUR)
     */
    public void registerPenBalance(Long pensionID, Float balance, String currency){
        Optional<PenBalance> optionalPenBalance = penBalanceRepository.findByPensionIDAndCurrency(pensionID, currency);

        // First we check if the Pension Balance already exists
        if(optionalPenBalance.isPresent()){
            throw new ApiIncorrectException("You already possess a balance with this currency");
        }

        Optional<PensionSaving> optionalPensionSaving = pensionSavingRepository.findByPensionID(pensionID);

        //We also check if the PensionSaving exists
        if(optionalPensionSaving.isEmpty()){
            throw new ApiNotFoundException("Pension saving with id : " + pensionID + " does not exist");
        }
        PenBalance penBalance = new PenBalance(optionalPensionSaving.get(), balance, currency);

        penBalanceRepository.save(penBalance);
    }

    /**
     * @param penBalance The PensionBalance that has to be updated
     * @param amount The amount
     */
    public void updateBalance(PenBalance penBalance, Float amount){
        //We have to check if we don't need to update the reward percentage
        if (penBalance.getBalance() + amount <= 990){
            penBalance.setBalance(penBalance.getBalance() + amount);
        }
        // In this case the total balance is between 990eur and 1270eur so we have to change the reward percentage
        else if (penBalance.getBalance() + amount > 990 && penBalance.getBalance() + amount <= 1270){
            penBalance.setBalance(penBalance.getBalance() + amount);

            Optional<PensionSaving> optionalPensionSaving = pensionSavingRepository.findByPensionID(penBalance.getPensionSaving().getPensionID());
            Optional<Insurance> optionalInsurance = insuranceRepository.findById(optionalPensionSaving.get().getPensionID());

            optionalPensionSaving.get().setPercentage(25f);
            optionalPensionSaving.get().setType("PEN25");
            optionalInsurance.get().setType("PEN25");
        }
        //Throw exception in the case the balance exceed the max amount.
        else{
            throw new ApiIncorrectException("The balance cannot exceed 1270€");
        }
    }
}
