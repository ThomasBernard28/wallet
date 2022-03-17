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

    @Query("SELECT s FROM WALLETS s WHERE s.walletID = ?1")
    Optional<Wallet> findWalletByWalletID(Long walletID);

    @Query("SELECT s FROM WALLETS s WHERE s.user.userID = ?1 and s.bic = ?2")
    Optional<Wallet> findWalletByUserAndBic(String userID, String bic);

    @Query("SELECT s FROM WALLETS s WHERE s.user=?1")
    List<Wallet> findWalletByUserEquals(User user);

    @Modifying
    @Query("DELETE FROM WALLETS s WHERE s.walletID = ?1")
    void deleteById(Long walletID);
}
