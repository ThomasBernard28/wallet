package com.example.Api.institution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    @Autowired
    public InstitutionService(InstitutionRepository institutionRepository){
        this.institutionRepository = institutionRepository;
    }

    public List<Institution> getInstitutions(){
        return institutionRepository.findAll();
    }

    public Optional<Institution> getOneInstitution(String bic){
        Optional<Institution> institution = institutionRepository.findInstitutionByBic(bic);

        if(!institution.isPresent()){
            throw new EntityNotFoundException("This institution doesn't exist");
        }

        return institution;
    }
}
