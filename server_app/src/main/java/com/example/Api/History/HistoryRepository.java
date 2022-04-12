package com.example.Api.History;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("SELECT s FROM TRX_HISTORY s WHERE s.historyIDEmb.balanceIdViewer = ?1")
    List<History> findByBalanceIdViewer(Long balanceIDViewer);

    @Query("SELECT s FROM TRX_HISTORY s WHERE s.historyIDEmb.trxID = ?1")
    List<History> findByTrxID(Long trxID);
}
