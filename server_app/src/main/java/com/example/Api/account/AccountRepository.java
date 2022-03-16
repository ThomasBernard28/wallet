package com.example.Api.account;

import com.example.Api.clientVsInstitution.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    List<Account> findAccountByWallet(Long walletID);

    Optional<Account> findByIban(String iban);

    List<Account> findAccountByClient(Client client);
}
