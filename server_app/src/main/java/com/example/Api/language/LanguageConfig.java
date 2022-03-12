
package com.example.Api.language;

import com.example.Api.user.User;
import com.example.Api.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LanguageConfig {

    @Bean
    CommandLineRunner languageRunner(LanguageRepository languageRepository, UserRepository userRepository){
        return args -> {
            Language french = new Language("FR");
            User user = new User("ugoproietti00122211708", "00122211708", "6789", "Proietti", "Ugo", french);

            userRepository.save(user);
        };
    }
}