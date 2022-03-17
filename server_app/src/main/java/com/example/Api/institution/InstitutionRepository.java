package com.example.Api.institution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface InstitutionRepository extends JpaRepository<Institution, String> {

    @Query("SELECT s FROM INSTITUTIONS s WHERE s.bic = ?1")
    Optional<Institution> findInstitutionByBic(String bic);
}
