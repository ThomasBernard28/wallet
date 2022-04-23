package com.example.Api.language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Language Repository
 */
@Repository
public interface LanguageRepository extends JpaRepository<Language, String> {

    /**
     * Query to find a language by its ID
     * @param language The identifier of the language we want to get
     * @return The language
     */
    @Query("SELECT s FROM LANGUAGES s WHERE s.language = ?1")
    Optional<Language> findByLanguage(String language);
}
