package com.example.Api.History;

import com.example.Api.account.Account;
import com.example.Api.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/history")
public class HistoryController {

    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService){
        this.historyService = historyService;
    }

    @GetMapping
    public List<History> findAll(){
        return historyService.findAll();
    }

    @GetMapping(path = {"{balanceIdViewer}"})
    public List<History> getAllTrxByViewer(@PathVariable("balanceIdViewer") Long balanceIdViewer){
        return historyService.getAllByViewer(balanceIdViewer);
    }

    @GetMapping(path = "iban/{iban}")
    public List<History> getAllTrxByIban(@PathVariable("iban") String iban){
        return historyService.getAllByAccount(iban);
    }

}
