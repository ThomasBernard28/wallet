package com.example.Api.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository){
        this.bankRepository = bankRepository;
    }

    public Bank getBank(String bic){
        Optional<Bank> bankOptional = bankRepository.findBankByBic(bic);

        if(!bankOptional.isPresent()){
            throw new EntityNotFoundException("This bank doesn't exist");

        }
        return bankOptional.get();
    }
}
