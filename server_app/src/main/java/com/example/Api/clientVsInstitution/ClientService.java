package com.example.Api.clientVsInstitution;

import com.example.Api.institution.Institution;
import com.example.Api.institution.InstitutionRepository;
import com.example.Api.institution.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final InstitutionRepository institutionRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, InstitutionRepository institutionRepository){
        this.clientRepository = clientRepository;
        this.institutionRepository = institutionRepository;
    }

    public List<Client> getClients(String bic){

        return clientRepository.findClientByInstitution(bic);
    }

    public void registerClient(String bic, String userID){
        Client client = new Client(institutionRepository.getById(bic), userID);


        Optional<Client> clientOptional = clientRepository.getClientByInstitutionAndAndUserID(client.getInstitution(), userID);


        if(clientOptional.isPresent()){
            throw new IllegalStateException("This client is already registered");
        }

        System.out.println(client);
        clientRepository.save(client);
    }

}
