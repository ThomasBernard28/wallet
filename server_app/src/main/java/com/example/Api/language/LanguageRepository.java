package com.example.Api.language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LanguageRepository extends JpaRepository<Language, String> {

    @Query("SELECT s FROM LANGUAGES s WHERE s.language = ?1")
    Language findByLanguage(String language);

    @Query("SELECT s FROM LANGUAGES s WHERE s.language = ?1")
    Optional<Language> findLanguageByLanguage(String language);
}
