package com.example.Api.institution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/institutions")
public class InstitutionController {

    private final InstitutionService institutionService;

    @Autowired
    public InstitutionController(InstitutionService institutionService){
        this.institutionService = institutionService;
    }

    @GetMapping
    public List<Institution> getInstitutions(){
        return institutionService.getInstitutions();
    }

    @GetMapping(path = "{bic}")
    public Institution getInstitution(@PathVariable(name = "bic") String bic){

        Optional<Institution> institution = institutionService.getOneInstitution(bic);

        if(!institution.isPresent()){
            throw new EntityNotFoundException("Institution " + bic + " not found");
        }
        return institution.get();
    }
}
