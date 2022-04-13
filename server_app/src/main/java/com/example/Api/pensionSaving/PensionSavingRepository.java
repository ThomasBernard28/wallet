package com.example.Api.pensionSaving;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PensionSavingRepository extends JpaRepository<PensionSaving, Long> {

    @Query(value = "SELECT * FROM PENSION_SAVINGS s WHERE s.pensionID = ?1 AND s.activity = 1", nativeQuery = true)
    Optional<PensionSaving> findByPensionID(Long pensionID);

    @Query(value = "SELECT * FROM PENSION_SAVINGS s WHERE s.userID = ?1 AND s.activity=1 ", nativeQuery = true)
    Optional<PensionSaving> findByUserID(String userID);
}
