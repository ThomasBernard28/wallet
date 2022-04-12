package com.example.Api.client;

import com.example.Api.bank.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, ClientIDEmb> {

    @Query("SELECT s FROM CLIENTS s WHERE s.clientIDEmb.bic = ?1")
    List<Client> findAllByBank(String bic);

    @Query("SELECT s FROM CLIENTS s WHERE s.clientIDEmb.bic = ?1 and s.clientIDEmb.userID = ?2")
    Optional<Client> findByBankAndUserID(String bic, String userID);

}