package com.example.Api.institution;

import com.example.Api.language.Language;
import com.example.Api.language.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstitutionConfig {

    @Bean
    CommandLineRunner institutionRunner(InstitutionRepository institutionRepository, LanguageRepository languageRepository){
        return args -> {
            Institution cphache = new Institution("CPHBBE75", "1234", "CPHACHE", languageRepository.getById("FR") );
            Institution bnpb = new Institution("GEBBABEB", "1234", "BNPB", languageRepository.getById("FR"));

            institutionRepository.save(cphache);
            institutionRepository.save(bnpb);
        };
    }
}
