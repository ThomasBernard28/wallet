package com.example.Api.History;


import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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

    @GetMapping(path = "before/{iban}/{dateTime}")
    public List<History> getAllBeforeDate(@PathVariable("iban") String iban, @PathVariable("dateTime")String dateTime){
        return historyService.findTrxBeforeDate(iban, LocalDateTime.parse(dateTime));
    }

    @GetMapping(path = "after/{iban}/{dateTime}")
    public List<History> getAllAfterDate(@PathVariable("iban") String iban, @PathVariable("dateTime")String dateTime){
        return historyService.findTrxAfterDate(iban, LocalDateTime.parse(dateTime));
    }

    @GetMapping(path = "between/{iban}/{startDateTime}/{endDateTime}")
    public List<History> getAllBetweenDate(@PathVariable("iban") String iban, @PathVariable("startDateTime")String startDateTime,
                                          @PathVariable("endDateTime")String endDateTime){
        return historyService.findTrxBetweenDates(iban, LocalDateTime.parse(startDateTime), LocalDateTime.parse(endDateTime));
    }


    @GetMapping(path = "iban/{iban}")
    public List<History> getAllTrxByIban(@PathVariable("iban") String iban){
        return historyService.getAllByAccount(iban);
    }

}
