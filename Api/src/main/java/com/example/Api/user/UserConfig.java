package com.example.Api.user;

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
                    "FR"
            );

            User Theo = new User(
                    "theogodin02090211708",
                    "02090817708",
                    "1234",
                    "Godin",
                    "Theo",
                    "FR"
            );

            userRepository.saveAll(
                    List.of(Thomas, Theo)
            );
        };
    }
}
