package com.example.Api.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientVsInstRepository extends JpaRepository<ClientVsInst, ClientVsInstID> {

    @Query("SELECT s FROM clients s WHERE s.bank.bic = ?1")
    List<ClientVsInst> findAllByBank(String bic);

    @Query("SELECT s FROM clients s WHERE s.bank.bic = ?1 and s.userID = ?2")
    Optional<ClientVsInst> findByBankAndUserID(String bic, String userID);

}
