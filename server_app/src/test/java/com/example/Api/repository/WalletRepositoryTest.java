package com.example.Api.repository;

import com.example.Api.language.LanguageRepository;
import com.example.Api.user.UserRepository;
import com.example.Api.wallet.WalletRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
    /*
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

     */
}
