package com.example.Api.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository){
        this.languageRepository = languageRepository;
    }

    public List<Language> getLanguages(){
        return languageRepository.findAll();
    }

    public Language getLanguage(String language){
        return languageRepository.findByLanguage(language);
    }
}
