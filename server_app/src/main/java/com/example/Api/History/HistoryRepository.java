package com.example.Api.History;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.balanceIdViewer = 1", nativeQuery = true)
    List<History> findByBalanceIdViewer(Long balanceIDViewer);

    @Query(value = "SELECT * FROM TRX_HISTORY ", nativeQuery = true)
    List<History> findAll();

    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.trxID = ?1", nativeQuery = true)
    List<History> findByTrxID(Long trxID);
}
