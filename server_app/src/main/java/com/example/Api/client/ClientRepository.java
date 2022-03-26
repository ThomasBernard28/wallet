package com.example.Api.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, ClientID> {

    @Query("SELECT s FROM CLIENT s WHERE s.bank.bic = ?1")
    List<Client> findAllByBank(String bic);

    @Query("SELECT s FROM CLIENT s WHERE s.bank.bic = ?1 and s.userID = ?2")
    Optional<Client> findByBankAndUserID(String bic, String userID);

}
