package com.example.Api.pendingRequests.accountRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Account Request Controller that catches HTTP requests
 */
@RestController
@RequestMapping(path = "api/v1/accRequest")
public class AccountRequestController {

    private final AccountRequestService accountRequestService;

    /**
     * Constructor
     * @param accountRequestService Account Request Service
     */
    @Autowired
    public AccountRequestController(AccountRequestService accountRequestService) {
        this.accountRequestService = accountRequestService;
    }

    /**
     * Method that catches a GET request asking for all Request by Institutions
     * @param bic The bic of the institutions we want the requests from
     * @return The List containing all requests
     */
    @GetMapping(path = "{bic}")
    public List<AccountRequest> getRequestByBank(@PathVariable("bic") String bic){
        return accountRequestService.getAllRequestByBank(bic);
    }

    /**
     * Method that catches a PUT request in order to validate a request so
     * the scheduler can create the Account
     * @param accRequestID The id of the request that has to be validated
     */
    @PutMapping(path = "validate/{accRequestID}")
    public void validateRequest(@PathVariable("accRequestID") Long accRequestID){
        accountRequestService.validateAccountRequest(accRequestID);
    }
}
