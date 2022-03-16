package com.example.Api.clientVsInstitution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping(path = "{bic}")
    public List<Client> getInstitutionClients(@PathVariable(name = "bic") String bic){
        return clientService.getClients(bic);
    }
}
