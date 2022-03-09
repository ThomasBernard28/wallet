package com.example.Api.wallet;

import com.example.Api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository  extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findWalletByWalletID(Long walletID);

    Optional<Wallet> findWalletByUserAndBic(String userID, String bic);

    List<Wallet> findWalletByUserEquals(User user);
}
