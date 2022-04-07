package com.example.Api.balance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    @Query("SELECT s FROM CASH_BALANCES s WHERE s.iban.iban = ?1")
    List<Balance> findByIban(String iban);

    @Query("SELECT s FROM CASH_BALANCES s WHERE s.balanceID = ?1")
    Optional<Balance> findByBalanceID(Long balanceID);

    @Query("SELECT s FROM CASH_BALANCES s WHERE s.iban.iban = ?1 AND s.currency = ?2")
    Optional<Balance> findByIbanAndCurrency(String iban, String currency);
}
