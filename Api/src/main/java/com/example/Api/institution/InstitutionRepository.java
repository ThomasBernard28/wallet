package com.example.Api.institution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, String> {

    Optional<Institution> findInstitutionByBic(String bic);
}
