package com.example.Api.user;

import com.example.Api.language.Language;
import com.example.Api.language.LanguageRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User Thomas = new User(
                    "thomasbernard02032811708",
                    "02032817708",
                    "1234",
                    "Bernard",
                    "Thomas",
                    new Language("FR")
            );

            User Theo = new User(
                    "theogodin02090211708",
                    "02090817708",
                    "1234",
                    "Godin",
                    "Theo",
                    new Language("FR")
            );

            userRepository.saveAll(
                    List.of(Thomas, Theo)
            );
        };
    }
}
