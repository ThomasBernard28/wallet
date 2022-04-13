package com.example.Api.pensionSaving;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PensionSavingRepository extends JpaRepository<PensionSaving, Long> {

    Optional<PensionSaving> findByPensionID(Long pensionID);
}
