package com.example.Api.clientVsInstitution;

import com.example.Api.institution.Institution;
import com.example.Api.institution.InstitutionRepository;
import com.example.Api.institution.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void registerClient(String bic, String userID){
        //Optional<Client> client = clientRepository.getClientByInstitutionAndAndUserID(institutionRepository.findInstitutionByBic(bic).get(), userID);
        Client realClient = new Client(institutionRepository.findInstitutionByBic(bic).get(), userID);
        /*
        if(client.isPresent()){
            throw new IllegalStateException("This client is already registered");
        }

         */
        clientRepository.save(realClient);
    }

}
