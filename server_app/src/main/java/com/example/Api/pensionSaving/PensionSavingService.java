package com.example.Api.pensionSaving;

import com.example.Api.client.ClientRepository;
import com.example.Api.exception.ApiIncorrectException;
import com.example.Api.exception.ApiNotFoundException;
import com.example.Api.insurance.InsuranceService;
import com.example.Api.insuranceInfo.InsuranceInfoRepository;
import com.example.Api.penBalance.PenBalanceService;
import com.example.Api.wallet.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Pension Saving Service layer
 */
@Service
public class PensionSavingService {

    private final PensionSavingRepository pensionSavingRepository;
    private final WalletRepository walletRepository;
    private final ClientRepository clientRepository;
    private final InsuranceInfoRepository insuranceInfoRepository;
    private final InsuranceService insuranceService;
    private final PenBalanceService penBalanceService;

    /**
     * Constructor
     * @param pensionSavingRepository Pension Saving Repository
     * @param walletRepository Wallet Repository
     * @param clientRepository Client Repository
     * @param insuranceInfoRepository Insurance Repository
     * @param insuranceService Insurance Service
     * @param penBalanceService Pension Balance Service
     */
    @Autowired
    public PensionSavingService(PensionSavingRepository pensionSavingRepository, WalletRepository walletRepository,
                                ClientRepository clientRepository, InsuranceInfoRepository insuranceInfoRepository,
                                InsuranceService insuranceService, PenBalanceService penBalanceService){
        this.pensionSavingRepository = pensionSavingRepository;
        this.clientRepository = clientRepository;
        this.walletRepository = walletRepository;
        this.insuranceInfoRepository = insuranceInfoRepository;
        this.insuranceService = insuranceService;
        this.penBalanceService = penBalanceService;
    }

    /**
     * Method to get a Pension Saving with a specific id
     * @param pensionID The id we want the Pension Saving from
     * @return The Pension Saving if found or throw and exception if not found
     */
    public PensionSaving getPensionSavingByID(Long pensionID){
        Optional<PensionSaving> optionalPensionSaving = pensionSavingRepository.findByPensionID(pensionID);

        if (optionalPensionSaving.isEmpty()){
            throw new ApiNotFoundException("Pension saving insurance with id : " + pensionID + " does not exist or is inactive");
        }
        return optionalPensionSaving.get();
    }

    /**
     * Method to get all the Pension Saving to reward. (After 1 year)
     * @param date The reference date
     * @return A list containing the Pension Savings to reward
     */
    public List<PensionSaving> pensionSavingsToReward(LocalDate date){
        return pensionSavingRepository.findByRenewDate(date);
    }

    /**
     *
     * @param walletID The walletID of the Wallet we want to add the Pension Saving to
     * @param userID The userID of the User that want to subscribe to the Pension Saving
     * @param bic The bic of the institution the Client is registered to
     * @param percentage The reward percentage
     * @param subDate The subscription date
     * @param renewDate The renewal date
     * @param type The type of Insurance (PEN30 in this case)
     * @param balance The balance (0 by default)
     * @param activity The activity (1 by default)
     */
    public void createPensionSaving(Long walletID, String userID, String bic, Float percentage, LocalDate subDate, LocalDate renewDate, String type, Float balance, Integer activity){

        if(walletRepository.findWalletByWalletID(walletID).isEmpty()){
            throw new ApiNotFoundException("Wallet with id : " + walletID + " doesn't exist or is inactive");
        }

        if (clientRepository.findByBankAndUserID(bic, userID).isEmpty()){
            throw new ApiNotFoundException("Client with userID : " + userID + " and bic : " + bic + " does not exist");
        }

        if(percentage != 30f){
            throw new ApiIncorrectException("The base percentage must be set to 30%");
        }

        if(pensionSavingRepository.findByUserID(userID).isPresent()){
            throw new ApiIncorrectException("You can only possess one active pension saving");
        }

        if(insuranceInfoRepository.findByInsType(type).isEmpty()){
            throw new ApiNotFoundException("Insurance type : " + type + " doesn't exist");
        }

        PensionSaving pensionSaving = new PensionSaving(walletID, bic, userID, subDate, renewDate, type, percentage, balance, activity);
        pensionSavingRepository.save(pensionSaving);

        insuranceService.createInsuranceFromPension(pensionSaving);

        penBalanceService.registerPenBalance(pensionSaving.getPensionID(), pensionSaving.getBalance(), "EUR");
    }
    /*
    @Transactional
    public void retributePension(PensionSaving pensionSaving){

    }

     */
}
