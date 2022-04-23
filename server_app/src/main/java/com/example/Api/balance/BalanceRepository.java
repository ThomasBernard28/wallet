package com.example.Api.balance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Balance Repository
 */
@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    /**
     * Query to get all the balance of an Account
     * @param iban The iban of the Account
     * @return List containing all balances
     */
    @Query("SELECT s FROM CASH_BALANCES s WHERE s.iban.iban = ?1")
    List<Balance> findByIban(String iban);

    /**
     * Query to get a balance by its balance ID
     * @param balanceID balanceID of the balance we want to get
     * @return An Optional containing the potential balance if found
     */
    @Query("SELECT s FROM CASH_BALANCES s WHERE s.balanceID = ?1")
    Optional<Balance> findByBalanceID(Long balanceID);

    /**
     * Query to get a balance by its iban and currency
     * @param iban the iban of the account the balance belongs to
     * @param currency the currency of the balance
     * @return An Optional containing the potential balance if found
     */
    @Query("SELECT s FROM CASH_BALANCES s WHERE s.iban.iban = ?1 AND s.currency = ?2")
    Optional<Balance> findByIbanAndCurrency(String iban, String currency);
}
