package com.example.Api.repository;

import com.example.Api.bank.Bank;
import com.example.Api.bank.BankRepository;
import com.example.Api.language.Language;
import com.example.Api.language.LanguageRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class BankRepositoryTest {

    @Autowired
    private BankRepository underTest;

    @Autowired
    private LanguageRepository languageRepository;

    @AfterEach
    void tearDown(){underTest.deleteAll();}

    @Test
    void shouldFindByBic(){
        Language language = new Language("FR");
        languageRepository.save(language);

        Bank bank = new Bank(
                "test",
                "psswd",
                "testName",
                language
        );
        underTest.save(bank);

        assertTrue(underTest.findBankByBic("test").isPresent());
    }

    @Test
    void shouldFindAllBanks(){
        Language language = new Language("FR");
        languageRepository.save(language);

        Bank bank = new Bank(
                "test",
                "psswd",
                "testName",
                language
        );
        underTest.save(bank);

        Bank bank2 = new Bank(
                "test2",
                "psswd",
                "testName",
                language
        );
        underTest.save(bank2);

        assertTrue(underTest.findAll().size() == 2 && underTest.findAll().get(0) != underTest.findAll().get(1));
    }
}
