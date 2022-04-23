package com.example.Api.bankIdentifiers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Bank Identifier Repository
 */
@Repository
public interface BankIdentifierRepository extends JpaRepository<BankIdentifier, Integer> {

    /**
     * Query to get the informations of the bank
     * @param bic The bic of the bank
     * @return An Optional containing the potential informations
     */
    @Query(value = "SELECT * FROM BANK_IDENTIFIER WHERE bic = ?1 ", nativeQuery = true)
    Optional<BankIdentifier> findByBank_Bic(String bic);

    /**
     * Query to update the latest given iban of a bank
     * @param lastIban the new iban
     * @param bic the bic of the bank
     */
    @Query(value = "UPDATE BANK_IDENTIFIER SET lastIban = ?1 WHERE bic = ?2", nativeQuery = true)
    void updateLastIban(Integer lastIban, String bic);
}
