package com.example.Api.bankIdentifiers;

import com.example.Api.exception.ApiNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankIdentifierService {

    private final BankIdentifierRepository bankIdentifierRepository;

    @Autowired
    public BankIdentifierService(BankIdentifierRepository bankIdentifierRepository) {
        this.bankIdentifierRepository = bankIdentifierRepository;
    }

    public BankIdentifier getByBic(String bic){
        Optional<BankIdentifier> optionalBankIdentifier = bankIdentifierRepository.findByBank_Bic(bic);

        if (optionalBankIdentifier.isEmpty()){
            throw new ApiNotFoundException("This bank : " + bic + " does not exist");
        }
        return optionalBankIdentifier.get();
    }

    public void updateLastIban(String bic, Integer lastIban){
        Optional<BankIdentifier> optionalBankIdentifier = bankIdentifierRepository.findByBank_Bic(bic);

        if (optionalBankIdentifier.isEmpty()){
            throw new ApiNotFoundException("Bank : " + bic + " does not exist");
        }
        bankIdentifierRepository.updateLastIban(lastIban, bic);
    }
}
