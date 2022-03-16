package com.example.Api.account;

import com.example.Api.clientVsInstitution.Client;
import com.example.Api.clientVsInstitution.ClientRepository;
import com.example.Api.institution.Institution;
import com.example.Api.institution.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final InstitutionRepository institutionRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, ClientRepository clientRepository, InstitutionRepository institutionRepository){
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.institutionRepository = institutionRepository;
    }

    public List<Account> getAccounts(Long walletID){
        return accountRepository.findAccountByWallet(walletID);
    }

    public List<Account> getAccountsForClient(String bic, String userID){
        Client client = clientRepository.getClientByInstitutionAndAndUserID(institutionRepository.getById(bic), userID);

        return accountRepository.findAccountByClient(client);
    }
}
