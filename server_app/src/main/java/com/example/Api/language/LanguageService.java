package com.example.Api.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Language Service
 */
@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    /**
     * Constructor
     * @param languageRepository Language Repository
     */
    @Autowired
    public LanguageService(LanguageRepository languageRepository){
        this.languageRepository = languageRepository;
    }

    /**
     * Get all languages
     * @return A List of all the languages that are supported by the app
     */
    public List<Language> getLanguages(){
        return languageRepository.findAll();
    }

    /**
     * Get one language
     * @param language The language we want
     * @return The language we want
     */
    public Language getLanguage(String language){
        return languageRepository.findByLanguage(language).get();
    }
}
