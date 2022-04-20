package com.example.Api.pensionSaving;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Pension Saving Repository. Its goal is to send requests to db and catch data that the db
 * returns
 */
@Repository
public interface PensionSavingRepository extends JpaRepository<PensionSaving, Long> {

    /**
     * Query to find a PensionSaving in the db by its id
     * @param pensionID the id of the PensionSaving we want
     * @return an Optional containing the potential PensionSaving that correspond to the id
     */
    @Query(value = "SELECT * FROM PENSION_SAVINGS s WHERE s.pensionID = ?1 AND s.activity = 1", nativeQuery = true)
    Optional<PensionSaving> findByPensionID(Long pensionID);

    /**
     * Query to find a PensionSaving by the id of its owner
     * @param userID The id of the user we want the PensionSaving from
     * @return an Optional containing the potential PensionSaving that correspond to the id
     */
    @Query(value = "SELECT * FROM PENSION_SAVINGS s WHERE s.userID = ?1 AND s.activity=1 ", nativeQuery = true)
    Optional<PensionSaving> findByUserID(String userID);

    /**
     * Query to find PensionSavings that have to be renewed (reward the user)
     * @param date The reference date (generally today)
     * @return A list containing PensionSaving that have to be renewed.
     */
    @Query(value = "SELECT * FROM PENSION_SAVINGS WHERE renewDate <= ?1", nativeQuery = true)
    List<PensionSaving> findByRenewDate(LocalDate date);
}
