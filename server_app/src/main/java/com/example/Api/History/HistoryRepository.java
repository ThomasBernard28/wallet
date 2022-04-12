package com.example.Api.History;

import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.balanceIdViewer = ?1", nativeQuery = true)
    List<History> findByBalanceIdViewer(Long balanceIDViewer);

    @Query(value = "SELECT * FROM TRX_HISTORY ", nativeQuery = true)
    List<History> findAll();

    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.trxID = ?1", nativeQuery = true)
    List<History> findByTrxID(Long trxID);

    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.balanceIdViewer = ?1 AND s.dateTime <= ?2", nativeQuery = true)
    List<History> findByBalanceIdViewerAndDateTimeBefore(Long balanceIdViewer, LocalDateTime dateTime);

    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.balanceIdViewer = ?1 AND s.dateTime >= ?2", nativeQuery = true)
    List<History> findByBalanceIdViewerAndDateTimeAfter(Long balanceIdViewer, LocalDateTime dateTime);

    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.balanceIdViewer = ?1 AND s.dateTime BETWEEN ?2 AND ?3", nativeQuery = true)
    List<History> findByBalanceIdViewerAndDateTimeBetween(Long balanceIdViewer, LocalDateTime starDateTime, LocalDateTime endDateTime);
}
