
package com.example.Api.penBalance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Pension Balance Repository to send queries to the db and catch data in return
 */
@Repository
public interface PenBalanceRepository extends JpaRepository<PenBalance, Long> {

    /**
     * @param pensionID The pension ID of the Pension Saving that contains this balance
     * @param currency The currency of the balance
     * @return An Optional containing a potential Pension Balance
     */
    @Query(value = "SELECT * FROM PENSION_BALANCE WHERE pensionID = ?1 AND currency = ?2", nativeQuery = true)
    Optional<PenBalance> findByPensionIDAndCurrency(Long pensionID, String currency);

    /**
     * @param penBalanceID The id of the balance we want to get
     * @return An Optional containing a potential Pension Balance that correspond to the ID
     */
    @Query(value = "SELECT * FROM PENSION_BALANCE WHERE penBalanceID = ?1", nativeQuery = true)
    Optional<PenBalance> findByPenBalanceID(Long penBalanceID);
}

