package com.example.Api.repository;

import com.example.Api.language.Language;
import com.example.Api.language.LanguageRepository;
import com.example.Api.user.User;
import com.example.Api.user.UserRepository;
import com.example.Api.wallet.Wallet;
import com.example.Api.wallet.WalletRepository;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class WalletRepositoryTest {

    @Autowired
    private WalletRepository underTest;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @AfterEach
    void tearDown(){underTest.deleteAll();}

    @Test
    void existByWalletID(){
        Language language = new Language("FR");
        languageRepository.save(language);

        LocalDate date =LocalDate.now();

        User testUser = new User(
                "testID",
                "natID",
                "psswd",
                "lastName",
                "firstName",
                language
        );

        Wallet wallet = new Wallet(
            1L,
            testUser,
            "GEBABBEB",
            date,
            1
        );
        underTest.save(wallet);



        //as the table is empty the base walletID is 1
        Wallet res = underTest.findWalletByWalletID(1L).get();
        assertTrue(res.getWalletID().equals(1L));
    }
}
