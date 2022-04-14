
package com.example.Api.penBalance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PenBalanceRepository extends JpaRepository<PenBalance, Long> {

    @Query(value = "SELECT * FROM PENSION_BALANCE WHERE pensionID = ?1 AND currency = ?2", nativeQuery = true)
    Optional<PenBalance> findByPensionIDAndCurrency(Long pensionID, String currency);

    @Query(value = "SELECT * FROM PENSION_BALANCE WHERE penBalanceID = ?1", nativeQuery = true)
    Optional<PenBalance> findByPenBalanceID(Long penBalanceID);
}

