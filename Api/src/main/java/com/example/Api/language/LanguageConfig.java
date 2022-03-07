package com.example.Api.language;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LanguageConfig {

    @Bean
    CommandLineRunner languageRunner(LanguageRepository languageRepository){
        return args -> {
            Language french = new Language(
                    "FR"
            );

            Language english = new Language(
                    "EN"
            );

            Language dutch = new Language(
                    "NL"
            );

            languageRepository.saveAll(
                    List.of(french, english, dutch)
            );
        };
    }
}
