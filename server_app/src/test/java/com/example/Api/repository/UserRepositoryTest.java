package com.example.Api.repository;

import com.example.Api.language.Language;
import com.example.Api.language.LanguageRepository;
import com.example.Api.user.User;
import com.example.Api.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;
    @Autowired
    private LanguageRepository languageRepository;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }


    @Test
    void findByUserID(){
        Language language = new Language("FR");
        languageRepository.save(language);

        User user = new User(
                "userID",
                "natID",
                "psswd",
                "lastName",
                "firstName",
                language
        );
        underTest.save(user);

        assertTrue(underTest.findUserByUserID("userID").isPresent());
    }

    @Test
    void shouldNotFindByUserID(){
        assertTrue(underTest.findUserByUserID("testID").isEmpty());
    }

}
