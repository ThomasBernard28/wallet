package com.example.Api.bankIdentifiers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BankIdentifierRepository extends JpaRepository<BankIdentifier, Integer> {

    @Query(value = "SELECT * FROM BANK_IDENTIFIER WHERE bic = ?1 ", nativeQuery = true)
    Optional<BankIdentifier> findByBank_Bic(String bic);

    @Query(value = "UPDATE BANK_IDENTIFIER SET lastIban = ?1 WHERE bic = ?2", nativeQuery = true)
    void updateLastIban(Integer lastIban, String bic);
}
