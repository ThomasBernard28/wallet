package com.example.Api.account;

import com.example.Api.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT s FROM ACCOUNTS s WHERE s.iban = ?1 AND s.activity = 1 ")
    Optional<Account> findByIban(String iban);

    @Query("SELECT s FROM ACCOUNTS s WHERE s.iban = ?1")
    Optional<Account> findByIbanToModify(String iban);

    @Query("SELECT s FROM ACCOUNTS s WHERE s.client = ?1 AND s.activity = 1")
    List<Account> findAccountByClient(Client client);

    @Query(value = "SELECT * FROM ACCOUNTS WHERE walletID = ?1 AND activity =1 UNION (SELECT a.iban 'iban', c.walletID_coOwner 'walletID', a.userID 'userID', a.bic 'bic', a.type 'type', a.avgBalance 'avgBalance', a.localCurr 'localCurr', a.activity 'activity' FROM ACCOUNTS a, CO_OWNER c WHERE a.iban = c.ibanOwner AND c.walletID_coOwner = ?1)", nativeQuery = true)
    List<Account> findAccountByWalletID(Long walletID);

    @Query(value = "UPDATE ACCOUNTS SET walletID = ?1 WHERE iban = ?2", nativeQuery = true)
    void addWalletToAccounts(Long wallletID, String iban);

    @Query(value = "SELECT * FROM ACCOUNTS WHERE bic = ?1", nativeQuery = true)
    List<Account> findAccountsByBic(String bic);
}
