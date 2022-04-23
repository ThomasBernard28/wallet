package com.example.Api.History;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * History Repository. Send queries to the db and catches the data back
 */
@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    /**
     * Query to get the history list of a cash balance
     * @param balanceIDViewer The balanceID of the cash balance
     * @return The history list of the cash balance
     */
    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.balanceIdViewer = ?1 ORDER BY dateTime", nativeQuery = true)
    List<History> findByBalanceIdViewer(Long balanceIDViewer);

    /**
     * Query to get all the history of the db
     * @return The history list of the all db
     */
    @Query(value = "SELECT * FROM TRX_HISTORY ORDER BY dateTime", nativeQuery = true)
    List<History> findAll();

    /**
     * Query to get the two parts of a transaction (sender and receiver)
     * @param trxID The transaction ID of the transaction
     * @return A List containing two History entities corresponding to each part of the transaction
     */
    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.trxID = ?1", nativeQuery = true)
    List<History> findByTrxID(Long trxID);

    /**
     * Query to get all the history of a balance before a specific date
     * @param balanceIdViewer The balanceID of the cash balance
     * @param dateTime The date and time
     * @return A List containing all the history entities before the specific date and time for the cash balance
     */
    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.balanceIdViewer = ?1 AND s.dateTime <= ?2 ORDER BY s.dateTime", nativeQuery = true)
    List<History> findByBalanceIdViewerAndDateTimeBefore(Long balanceIdViewer, LocalDateTime dateTime);

    /**
     * Query to get all the history of a balance after a specific date
     * @param balanceIdViewer The balanceID of the cash balance
     * @param dateTime The date and time
     * @return A List containing all the history entities after the specific date and time for the cash balance
     */
    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.balanceIdViewer = ?1 AND s.dateTime >= ?2 ORDER BY s.dateTime", nativeQuery = true)
    List<History> findByBalanceIdViewerAndDateTimeAfter(Long balanceIdViewer, LocalDateTime dateTime);

    /**
     * Query to get all the history of a balance between two specific dates
     * @param balanceIdViewer The balanceID of the cash balance
     * @param starDateTime The start date and time
     * @param endDateTime The end date and time
     * @return A List containing all the history entities between the specific dates and times for the cash balance
     */
    @Query(value = "SELECT * FROM TRX_HISTORY s WHERE s.balanceIdViewer = ?1 AND s.dateTime BETWEEN ?2 AND ?3 ORDER BY s.dateTime ", nativeQuery = true)
    List<History> findByBalanceIdViewerAndDateTimeBetween(Long balanceIdViewer, LocalDateTime starDateTime, LocalDateTime endDateTime);

    /**
     * Query to insert a Transaction History between two account or a cash deposit
     * @param trxID The id of the transaction
     * @param balanceID The balanceID to identify the part of the transaction (sender or receiver)
     * @param amount The total amount of the transaction
     * @param dateTime The date and time the transaction was performed
     * @param nextBalance The balance after the transaction
     * @param prevBalance The balance before the transaction
     * @param ibanReceiver The iban of the receiver Account
     * @param ibanSender The iban of the sender Account
     * @param comments The eventual comments
     */
    @Query(value = "INSERT INTO TRX_HISTORY (trxID, balanceIdViewer, amount, dateTime, nextBalance, prevBalance, ibanReceiver, ibanSender, comments)  " +
            "VALUES(?1, ?2, ?3, ?4, ?5, ?6 , ?7, ?8, ?9) ", nativeQuery = true)
    void insertHistory(Long trxID, Long balanceID, Float amount, LocalDateTime dateTime, Float nextBalance, Float prevBalance, String ibanReceiver, String ibanSender, String comments);

    /**
     * Query to insert a Insurance Transaction History between one account and one insurance balance
     * @param trxID The id of the transaction
     * @param balanceID The balanceID of the Cash Balance funding the insurance
     * @param amount The total amount of the transaction
     * @param dateTime The date and time the transaction was peformed
     * @param nextBalance The balance of the insurance after the transaction
     * @param prevBalance The balance of the insurance before the transaciton
     * @param ibanSender The iban of the Sender Account
     * @param insIDReceiver The id of receiver insurance
     */
    @Query(value = "INSERT INTO TRX_HISTORY (trxID, balanceIdViewer, amount, dateTime, nextBalance, prevBalance, ibanSender, insIDReceiver)  " +
            "VALUES(?1, ?2, ?3, ?4, ?5, ?6 , ?7, ?8) ", nativeQuery = true)
    void insertInsHistory(Long trxID, Long balanceID, Float amount, LocalDateTime dateTime, Float nextBalance, Float prevBalance, String ibanSender, Long insIDReceiver);

}