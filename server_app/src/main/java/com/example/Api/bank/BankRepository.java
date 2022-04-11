package com.example.Api.bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, String> {

    @Query("SELECT s FROM INSTITUTIONS s WHERE s.bic = ?1")
    Optional<Bank> findBankByBic(String bic);

    @Query("SELECT s FROM INSTITUTIONS s")
    List<Bank> findAll();
}
