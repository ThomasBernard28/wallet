package com.example.Api.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Transaction repository sends request to the data base
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Request that return a Transaction by its trxID
     * @param trxID The trxID we want the Transaction from
     * @return The transaction that has the trxID for ID
     */
    @Query("SELECT s FROM TRANSACTIONS s WHERE s.trxID = ?1 ")
    Optional<Transaction> findByTrxID(Long trxID);

    /**
     * Request that return the List of Transaction that has a date before the
     * date and time given in parameter
     * @param dateTime The date time
     * @return The List of Transaction before the date time
     */
    @Query("SELECT s FROM TRANSACTIONS s WHERE s.dateTime <= ?1 AND s.status = 0")
    List<Transaction> findByDateTime(LocalDateTime dateTime);
}
