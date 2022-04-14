package com.example.Api.penBalance;

import com.example.Api.exception.ApiIncorrectException;
import com.example.Api.exception.ApiNotFoundException;
import com.example.Api.pensionSaving.PensionSaving;
import com.example.Api.pensionSaving.PensionSavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PenBalanceService {

    private final PenBalanceRepository penBalanceRepository;
    private final PensionSavingRepository pensionSavingRepository;

    @Autowired
    public PenBalanceService(PenBalanceRepository penBalanceRepository, PensionSavingRepository pensionSavingRepository){
        this.penBalanceRepository = penBalanceRepository;
        this.pensionSavingRepository = pensionSavingRepository;
    }

    public PenBalance findByPensionIdAndCurrency(Long pensionID, String currency){
        if(pensionSavingRepository.findByPensionID(pensionID).isEmpty()){
            throw new ApiNotFoundException("The Pension Saving with id : " + pensionID + " doesn't exist");
        }
        Optional<PenBalance> optionalPenBalance = penBalanceRepository.findByPensionIDAndCurrency(pensionID, currency);

        if(optionalPenBalance.isEmpty()){
            throw new ApiNotFoundException("Pension balance with id : " + pensionID + " and currency : " + currency + " does not exist");
        }
        return optionalPenBalance.get();
    }

    public PenBalance findByPenBalanceID(Long penBalanceID){
        Optional<PenBalance> optionalPenBalance = penBalanceRepository.findByPenBalanceID(penBalanceID);

        if(optionalPenBalance.isEmpty()){
            throw new ApiNotFoundException("Pension balance with id : " + penBalanceID + " does not exist");
        }

        return optionalPenBalance.get();
    }

    public void registerPenBalance(Long pensionID, Float balance, String currency){
        Optional<PenBalance> optionalPenBalance = penBalanceRepository.findByPensionIDAndCurrency(pensionID, currency);

        if(optionalPenBalance.isPresent()){
            throw new ApiIncorrectException("You already possess a balance with this currency");
        }

        Optional<PensionSaving> optionalPensionSaving = pensionSavingRepository.findByPensionID(pensionID);

        if(optionalPensionSaving.isEmpty()){
            throw new ApiNotFoundException("Pension saving with id : " + pensionID + " does not exist");
        }

        PenBalance penBalance = new PenBalance(optionalPensionSaving.get(), balance, currency);

        penBalanceRepository.save(penBalance);
    }

    public void updateBalance(PenBalance penBalance, Float amount){
        if (penBalance.getBalance() + amount <= 990){
            penBalance.setBalance(penBalance.getBalance() + amount);
        }
        else if (penBalance.getBalance() + amount > 990 && penBalance.getBalance() + amount <= 1270){
            penBalance.setBalance(penBalance.getBalance() + amount);

            Optional<PensionSaving> optionalPensionSaving = pensionSavingRepository.findByPensionID(penBalance.getPensionSaving().getPensionID());

            optionalPensionSaving.get().setPercentage(25f);
            optionalPensionSaving.get().setType("PEN25");
        }
        else{
            throw new ApiIncorrectException("The balance cannot exceed 1270€");
        }
    }
}
