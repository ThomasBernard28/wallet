package com.example.Api.History;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.balanceIdViewer = ?1 ORDER BY dateTime", nativeQuery = true)
    List<History> findByBalanceIdViewer(Long balanceIDViewer);

    @Query(value = "SELECT * FROM TRX_HISTORY ORDER BY dateTime", nativeQuery = true)
    List<History> findAll();

    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.trxID = ?1", nativeQuery = true)
    List<History> findByTrxID(Long trxID);

    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.balanceIdViewer = ?1 AND s.dateTime <= ?2 ORDER BY s.dateTime", nativeQuery = true)
    List<History> findByBalanceIdViewerAndDateTimeBefore(Long balanceIdViewer, LocalDateTime dateTime);

    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.balanceIdViewer = ?1 AND s.dateTime >= ?2 ORDER BY s.dateTime", nativeQuery = true)
    List<History> findByBalanceIdViewerAndDateTimeAfter(Long balanceIdViewer, LocalDateTime dateTime);

    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.balanceIdViewer = ?1 AND s.dateTime BETWEEN ?2 AND ?3 ORDER BY s.dateTime ", nativeQuery = true)
    List<History> findByBalanceIdViewerAndDateTimeBetween(Long balanceIdViewer, LocalDateTime starDateTime, LocalDateTime endDateTime);

    @Query(value = "INSERT INTO TRX_HISTORY (trxID, balanceIdViewer, amount, dateTime, nextBalance, prevBalance, ibanReceiver, ibanSender, comments)  " +
            "VALUES(?1, ?2, ?3, ?4, ?5, ?6 , ?7, ?8, ?9) ", nativeQuery = true)
    void insertHistory(Long trxID, Long balanceID, Float amount, LocalDateTime dateTime, Float nextBalance, Float prevBalance, String ibanReceiver, String ibanSender, String comments);

    @Query(value = "INSERT INTO TRX_HISTORY (trxID, balanceIdViewer, amount, dateTime, nextBalance, prevBalance, ibanReceiver, insIDReceiver)  " +
            "VALUES(?1, ?2, ?3, ?4, ?5, ?6 , ?7, ?8) ", nativeQuery = true)
    void insertInsHistory(Long trxID, Long balanceID, Float amount, LocalDateTime dateTime, Float nextBalance, Float prevBalance, String ibanReceiver, Long insIDReceiver);

}