package com.example.Api.bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Bank Repository
 */
@Repository
public interface BankRepository extends JpaRepository<Bank, String> {

    /**
     * Query to get a bank by its bic
     * @param bic The bic of the bank
     * @return an Optional containing the potential bank
     */
    @Query("SELECT s FROM INSTITUTIONS s WHERE s.bic = ?1")
    Optional<Bank> findBankByBic(String bic);

    /**
     * Query to get all banks
     * @return A List containing all banks
     */
    @Query("SELECT s FROM INSTITUTIONS s")
    List<Bank> findAll();
}
