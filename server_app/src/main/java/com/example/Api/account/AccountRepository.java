package com.example.Api.account;

import com.example.Api.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT s FROM ACCOUNTS s WHERE s.wallet.walletID = ?1 AND s.activity = 1")
    List<Account> findAccountByWallet(Long walletID);

    @Query("SELECT s FROM ACCOUNTS s WHERE s.iban = ?1 AND s.activity = 1 ")
    Optional<Account> findByIban(String iban);

    @Query("SELECT s FROM ACCOUNTS s WHERE s.client = ?1 AND s.activity = 1")
    List<Account> findAccountByClient(Client client);

}
