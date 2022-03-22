package com.example.Api.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/clientVs")
public class ClientVsInstController {

    private final ClientVsInstService clientVsInstService;

    @Autowired
    public ClientVsInstController(ClientVsInstService clientVsInstService){
        this.clientVsInstService = clientVsInstService;
    }

    @GetMapping(path = "{bic}")
    public List<ClientVsInst> getInstClients(@PathVariable("bic") String bic){
        return clientVsInstService.getAllBankClients(bic);
    }

    @GetMapping(path = "{bic}/{userID}")
    public ClientVsInst getOneClient(@PathVariable("bic") String bic, @PathVariable("userID") String userID){
        return clientVsInstService.getOneClient(bic, userID);
    }

    @PostMapping
    public void registerNewClient(@RequestBody Map<String, String> json){
        String bic = json.get("bic");
        String userID = json.get("userID");

        clientVsInstService.registerClient(bic, userID);
    }
}
