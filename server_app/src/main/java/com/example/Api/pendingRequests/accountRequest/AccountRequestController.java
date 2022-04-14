package com.example.Api.pendingRequests.accountRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/accRequest")
public class AccountRequestController {

    private final AccountRequestService accountRequestService;

    @Autowired
    public AccountRequestController(AccountRequestService accountRequestService) {
        this.accountRequestService = accountRequestService;
    }

    @GetMapping(path = "{bic}")
    public List<AccountRequest> getRequestByBank(@PathVariable("bic") String bic){
        return accountRequestService.getAllRequestByBank(bic);
    }

    @PutMapping(path = "validate/{accRequestID}")
    public void validateRequest(@PathVariable("accRequestID") Long accRequestID){
        accountRequestService.validateAccountRequest(accRequestID);
    }
}
