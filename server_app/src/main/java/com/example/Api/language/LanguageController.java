package com.example.Api.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Language Controller
 */
@RestController
@RequestMapping(path = "api/v1/language")
public class LanguageController {

    private final LanguageService languageService;

    /**
     * Constructor
     * @param languageService Language Service
     */
    @Autowired
    public LanguageController(LanguageService languageService){
        this.languageService = languageService;
    }

    /**
     * @return A List with all the languages supported by the application
     */
    @GetMapping
    public List<Language> getLanguages(){
        return languageService.getLanguages();
    }
}
