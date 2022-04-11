package com.example.Api.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT s FROM TRANSACTIONS s WHERE s.trxID = ?1 ")
    Optional<Transaction> findByTrxID(Long trxID);

    @Query("SELECT s FROM TRANSACTIONS s WHERE s.dateTime <= ?1 AND s.status = 0")
    List<Transaction> findByDateTime(LocalDateTime dateTime);
}
