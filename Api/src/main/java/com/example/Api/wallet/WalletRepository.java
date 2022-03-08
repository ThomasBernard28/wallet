package com.example.Api.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository  extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findWalletByWalletID(Long walletID);

    Optional<Wallet> findWalletByUserIDAndBic(String userID, String bic);

    List<Wallet> findWalletByUserIDEquals(String userID);
}
