package com.example.Api.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Wallet Repository
 */
@Repository
@Transactional
public interface WalletRepository  extends JpaRepository<Wallet, Long> {

    /**
     * Query to get a Wallet by its walletID
     * @param walletID walletID of the Wallet
     * @return an Optional containing the potential Wallet
     */
    @Query(value = "SELECT * FROM WALLETS WHERE walletID = ?1", nativeQuery = true)
    Optional<Wallet> findWalletByWalletID(Long walletID);

    /**
     * Query to get the wallet of a Client (userID, bic)
     * @param userID the userID of the Client
     * @param bic the bic of the bank the Client is registered in
     * @return An Optional containing the potential Wallet
     */
    @Query(value = "SELECT * FROM WALLETS  WHERE userID = ?1 and bic = ?2 AND activity = 1", nativeQuery = true)
    Optional<Wallet> findWalletByUserAndBic(String userID, String bic);

    /**
     * Query to get all the wallets of a User
     * @param userID the userID of the User
     * @return a List containing all the wallets of the User
     */
    @Query(value = "SELECT * FROM WALLETS WHERE userID=?1 AND activity = 1", nativeQuery = true)
    List<Wallet> findWalletByUserEquals(String userID);

    /**
     * Query to get all the wallets of a User including inactive ones
     * @param userID the userID of the User
     * @return a List containing all the wallets of User including the inactives ones
     */
    @Query(value = "SELECT * FROM WALLETS  WHERE userID=?1", nativeQuery = true)
    List<Wallet> findWalletByUserEqualsAll(String userID);

    /**
     * Query to delete a wallet from the db
     * @param walletID the walletID of the Wallet to delete
     */
    @Modifying
    @Query(value = "DELETE FROM WALLETS  WHERE walletID = ?1", nativeQuery = true)
    void deleteById(Long walletID);
}
