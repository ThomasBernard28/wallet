package com.example.Api.History;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * History controller to catch HTTP requests
 */
@RestController
@RequestMapping(path = "api/v1/history")
public class HistoryController {

    private final HistoryService historyService;

    /**
     * Constructor
     * @param historyService History Service providing method calls
     */
    @Autowired
    public HistoryController(HistoryService historyService){
        this.historyService = historyService;
    }

    /**
     * Method to catch a GET request asking for the entire database history
     * @return A list containing each transaction history
     */
    @GetMapping
    public List<History> findAll(){
        return historyService.findAll();
    }

    /**
     * Method to catch a GET request asking for the history of specific Cash Balance
     * @param balanceIdViewer The balanceID of the Cash Balance we want the history from
     * @return A List representing the transaction history of the balance
     */
    @GetMapping(path = {"{balanceIdViewer}"})
    public List<History> getAllTrxByViewer(@PathVariable("balanceIdViewer") Long balanceIdViewer){
        return historyService.getAllByViewer(balanceIdViewer);
    }

    /**
     * Method to catch a GET request asking for the history of an account before a specific date
     * @param iban The iban of the account
     * @param dateTime The date and time
     * @return A List representing the transaction history of the account before the date and time
     */
    @GetMapping(path = "before/{iban}/{dateTime}")
    public List<History> getAllBeforeDate(@PathVariable("iban") String iban, @PathVariable("dateTime")String dateTime){
        return historyService.findTrxBeforeDate(iban, LocalDateTime.parse(dateTime));
    }

    /**
     * Method to catch a GET request asking for the history of an account after a specific date
     * @param iban The iban of the account
     * @param dateTime The date and time
     * @return A List representing the transaction history of the account after the date and time
     */
    @GetMapping(path = "after/{iban}/{dateTime}")
    public List<History> getAllAfterDate(@PathVariable("iban") String iban, @PathVariable("dateTime")String dateTime){
        return historyService.findTrxAfterDate(iban, LocalDateTime.parse(dateTime));
    }

    /**
     * Method to catch a GET request asking for the history of an account between two specific dates
     * @param iban The iban of the account
     * @param startDateTime The date and time for the beginning of the period
     * @param endDateTime The date and time for the end of the period
     * @return A List representing the transaction history of the account between the dates and times
     */
    @GetMapping(path = "between/{iban}/{startDateTime}/{endDateTime}")
    public List<History> getAllBetweenDate(@PathVariable("iban") String iban, @PathVariable("startDateTime")String startDateTime,
                                          @PathVariable("endDateTime")String endDateTime){
        return historyService.findTrxBetweenDates(iban, LocalDateTime.parse(startDateTime), LocalDateTime.parse(endDateTime));
    }

    /**
     * Method to catch a GET request asking for the history of an Account
     * @param iban The iban of the account
     * @return A List representing the transaction history of the account
     */
    @GetMapping(path = "iban/{iban}")
    public List<History> getAllTrxByIban(@PathVariable("iban") String iban){
        return historyService.getAllByAccount(iban);
    }

}
