package com.example.Api.bankIdentifiers;

import com.example.Api.exception.ApiNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Bank Identifier Service
 */
@Service
public class BankIdentifierService {

    private final BankIdentifierRepository bankIdentifierRepository;

    /**
     * Constructor
     * @param bankIdentifierRepository Bank Identifier Repository
     */
    @Autowired
    public BankIdentifierService(BankIdentifierRepository bankIdentifierRepository) {
        this.bankIdentifierRepository = bankIdentifierRepository;
    }

    /**
     * Method to get the identifiers of a bank using a Repository method
     * @param bic the bic of the bank
     * @return The identifiers
     */
    public BankIdentifier getByBic(String bic){
        Optional<BankIdentifier> optionalBankIdentifier = bankIdentifierRepository.findByBank_Bic(bic);

        //If identifiers not found throw an exception
        if (optionalBankIdentifier.isEmpty()){
            throw new ApiNotFoundException("This bank : " + bic + " does not exist");
        }
        return optionalBankIdentifier.get();
    }

    /**
     * Method to update the latest given iban calling a Repository method
     * @param bic the bic of the bank
     * @param lastIban the new iban to insert
     */
    public void updateLastIban(String bic, Integer lastIban){
        Optional<BankIdentifier> optionalBankIdentifier = bankIdentifierRepository.findByBank_Bic(bic);

        //if the bank does not exist throw an exception
        if (optionalBankIdentifier.isEmpty()){
            throw new ApiNotFoundException("Bank : " + bic + " does not exist");
        }
        //else update
        bankIdentifierRepository.updateLastIban(lastIban, bic);
    }
}
