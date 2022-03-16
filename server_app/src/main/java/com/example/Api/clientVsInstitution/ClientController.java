package com.example.Api.clientVsInstitution;

import com.example.Api.institution.Institution;
import com.example.Api.institution.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(name = "api/v1/clients")
public class ClientController {

    private final ClientService clientService;
    private final InstitutionService institutionService;

    @Autowired
    public ClientController(ClientService clientService, InstitutionService institutionService){
        this.clientService = clientService;
        this.institutionService = institutionService;
    }

    @GetMapping(path = "{bic}")
    public List<Client> getInstitutionClients(@PathVariable(name = "bic") String bic){
        return clientService.getClients(bic);
    }

    @PostMapping()
    public void addClient(@RequestBody Map<String, String> json){

        clientService.registerClient(json.get("bic"), json.get("userID"));
    }
}
