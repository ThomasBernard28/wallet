package com.example.Api.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Client Repository
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, ClientIDEmb> {

    /**
     * Query to get all the clients of an Institution/Bank
     * @param bic The bic of the Institution/Bank
     * @return A List containing all clients
     */
    @Query("SELECT s FROM CLIENTS s WHERE s.clientIDEmb.bic = ?1")
    List<Client> findAllByBank(String bic);

    /**
     * Query to get a specific client
     * @param bic The bic of the institution the client is registered in
     * @param userID The userID of the Client
     * @return An Optional containing the potential client
     */
    @Query("SELECT s FROM CLIENTS s WHERE s.clientIDEmb.bic = ?1 and s.clientIDEmb.userID = ?2")
    Optional<Client> findByBankAndUserID(String bic, String userID);

}
