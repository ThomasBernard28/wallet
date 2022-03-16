package com.example.Api.clientVsInstitution;

import com.example.Api.institution.Institution;
import com.example.Api.institution.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private InstitutionRepository institutionRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients(String bic){
        Institution institution = institutionRepository.getById(bic);

        return clientRepository.findClientByInstitution(institution);
    }

}
