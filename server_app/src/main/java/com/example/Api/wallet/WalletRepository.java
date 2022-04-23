package com.example.Api.wallet;

import com.example.Api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface WalletRepository  extends JpaRepository<Wallet, Long> {

    @Query(value = "SELECT * FROM WALLETS WHERE walletID = ?1", nativeQuery = true)
    Optional<Wallet> findWalletByWalletID(Long walletID);

    @Query(value = "SELECT * FROM WALLETS  WHERE userID = ?1 and bic = ?2 AND activity = 1", nativeQuery = true)
    Optional<Wallet> findWalletByUserAndBic(String userID, String bic);

    @Query(value = "SELECT * FROM WALLETS WHERE userID=?1 AND activity = 1", nativeQuery = true)
    List<Wallet> findWalletByUserEquals(String userID);

    @Query(value = "SELECT * FROM WALLETS  WHERE userID=?1", nativeQuery = true)
    List<Wallet> findWalletByUserEqualsAll(String userID);

    @Modifying
    @Query(value = "DELETE FROM WALLETS  WHERE walletID = ?1", nativeQuery = true)
    void deleteById(Long walletID);
}
