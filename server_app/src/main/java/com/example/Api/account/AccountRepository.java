package com.example.Api.account;

import com.example.Api.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Account Repository
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    /**
     * Query to get an Account by its iban
     * @param iban the iban of the Account we want
     * @return An Optional containing the potential Account
     */
    @Query("SELECT s FROM ACCOUNTS s WHERE s.iban = ?1 AND s.activity = 1 ")
    Optional<Account> findByIban(String iban);

    /**
     * Query to get Account that is inactive with the objective to activate it back
     * @param iban the iban of the Account
     * @return an Optional containing the potential Account
     */
    @Query("SELECT s FROM ACCOUNTS s WHERE s.iban = ?1")
    Optional<Account> findByIbanToModify(String iban);

    /**
     * Query to get all the accounts of a Client
     * @param client the Client instance
     * @return A List containing all the Accounts of the client
     */
    @Query("SELECT s FROM ACCOUNTS s WHERE s.client = ?1 AND s.activity = 1")
    List<Account> findAccountByClient(Client client);

    /**
     * Query to get all the accounts of a wallet including the co Owned ones
     * @param walletID the walletID of the Wallet
     * @return A List containing all the Accounts of the Wallet
     */
    @Query(value = "SELECT * FROM ACCOUNTS WHERE walletID = ?1 AND activity =1 UNION (SELECT a.iban 'iban', c.walletID_coOwner 'walletID', a.userID 'userID', a.bic 'bic', a.type 'type', a.avgBalance 'avgBalance', a.localCurr 'localCurr', a.activity 'activity' FROM ACCOUNTS a, CO_OWNER c WHERE a.iban = c.ibanOwner AND c.walletID_coOwner = ?1)", nativeQuery = true)
    List<Account> findAccountByWalletID(Long walletID);

    /**
     * Query to get all Accounts of an institution by its bic
     * @param bic the bic of the institution
     * @return A List containing all the Accounts of the institution
     */
    @Query(value = "SELECT * FROM ACCOUNTS WHERE bic = ?1", nativeQuery = true)
    List<Account> findAccountsByBic(String bic);

    /**
     * Query to update an Account by adding it a Wallet
     * @param wallletID the walletID of the wallet the account will now belongs to
     * @param iban the iban of the Account
     */
    @Query(value = "UPDATE ACCOUNTS SET walletID = ?1 WHERE iban = ?2", nativeQuery = true)
    void addWalletToAccounts(Long wallletID, String iban);

}
