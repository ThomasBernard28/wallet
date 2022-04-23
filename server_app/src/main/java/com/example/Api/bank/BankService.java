package com.example.Api.bank;

import com.example.Api.exception.ApiNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Bank service
 */
@Service
public class BankService {

    private final BankRepository bankRepository;

    /**
     * Constructor
     * @param bankRepository Bank repository
     */
    @Autowired
    public BankService(BankRepository bankRepository){
        this.bankRepository = bankRepository;
    }

    /**
     * Method to get a bank by its bic
     * @param bic The bic of the bank
     * @return The bank if found. Throw an exception if not
     */
    public Bank getBank(String bic){
        Optional<Bank> bankOptional = bankRepository.findBankByBic(bic);

        //If the bank  does not exist throw an exception
        if(bankOptional.isEmpty()){
            throw new ApiNotFoundException("This bank : " + bic+ " doesn't exist");

        }
        //else return the bank
        return bankOptional.get();
    }

    /**
     * Method to get all the banks
     * @return a List containing all the banks
     */
    public List<Bank> getBanks(){
        return bankRepository.findAll();
    }
}
