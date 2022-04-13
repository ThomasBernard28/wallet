package com.example.Api.pensionSaving;

import com.example.Api.client.ClientRepository;
import com.example.Api.client.ClientService;
import com.example.Api.exception.ApiIncorrectException;
import com.example.Api.exception.ApiNotFoundException;
import com.example.Api.wallet.Wallet;
import com.example.Api.wallet.WalletRepository;
import com.example.Api.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PensionSavingService {

    private final PensionSavingRepository pensionSavingRepository;
    private final WalletRepository walletRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public PensionSavingService(PensionSavingRepository pensionSavingRepository, WalletRepository walletRepository, ClientRepository clientRepository){
        this.pensionSavingRepository = pensionSavingRepository;
        this.clientRepository = clientRepository;
        this.walletRepository = walletRepository;
    }

    public PensionSaving getPensionSavingByID(Long pensionID){
        Optional<PensionSaving> optionalPensionSaving = pensionSavingRepository.findByPensionID(pensionID);

        if (optionalPensionSaving.isEmpty()){
            throw new ApiNotFoundException("Pension saving insurance with id : " + pensionID + " does not exist or is inactive");
        }
        return optionalPensionSaving.get();
    }

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
        PensionSaving pensionSaving = new PensionSaving(walletID, bic, userID, subDate, renewDate, type, percentage, balance, activity);
        pensionSavingRepository.save(pensionSaving);

    }
}
