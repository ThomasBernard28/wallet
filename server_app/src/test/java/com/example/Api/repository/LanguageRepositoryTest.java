package com.example.Api.repository;

import com.example.Api.language.Language;
import com.example.Api.language.LanguageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class LanguageRepositoryTest {

    @Autowired
    private LanguageRepository underTest;


    @AfterEach
    void tearDown(){underTest.deleteAll();}


    @Test
    void shouldFindByLanguage(){
        Language language = new Language("FR");
        underTest.save(language);

        Language res = underTest.findByLanguage("FR").get();

        assertEquals(language.getLanguage(), res.getLanguage());
    }
}
