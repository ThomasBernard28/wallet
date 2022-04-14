package com.example.Api.pendingRequests.accountRequest;

import com.example.Api.account.AccountService;
import com.example.Api.bank.Bank;
import com.example.Api.bank.BankRepository;
import com.example.Api.client.Client;
import com.example.Api.exception.ApiNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccountRequestService {

    private final AccountRequestRepository accountRequestRepository;
    private final BankRepository bankRepository;

    @Autowired
    public AccountRequestService(AccountRequestRepository accountRequestRepository, BankRepository bankRepository) {
        this.accountRequestRepository = accountRequestRepository;
        this.bankRepository = bankRepository;
    }

    public List<AccountRequest> getAllRequestToProcess(){
        return accountRequestRepository.requestToProcess();
    }

    public List<AccountRequest> getAllRequestByBank(String bic){
        Optional<Bank> bankOptional = bankRepository.findBankByBic(bic);

        if(bankOptional.isEmpty()){
            throw new ApiNotFoundException("Bank with id : " + bic + " does not exist");
        }

        return accountRequestRepository.findByBank(bic);
    }

    public void registerAccountRequest(Client client, String type){
        AccountRequest accountRequest = new AccountRequest(client, type, 0, 0, "");

        accountRequestRepository.save(accountRequest);
    }

    @Transactional
    public void validateAccountRequest(Long accRequestID){
        Optional<AccountRequest> optionalAccountRequest = accountRequestRepository.findByAccRequestID(accRequestID);

        if(optionalAccountRequest.isEmpty()){
            throw new ApiNotFoundException("Account creation request with ID : " + accRequestID + " does not exist");
        }
        optionalAccountRequest.get().setValidator(1);
    }

    @Transactional
    public void updateRequestStatus(AccountRequest request){
        request.setStatus(1);
    }

    public void deleteRequest(AccountRequest request){
        accountRequestRepository.delete(request);
    }
}
