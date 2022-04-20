package com.example.Api.pendingRequests.accountRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Account Request Repository that send queries to the db and catches data back
 */
@Repository
public interface AccountRequestRepository extends JpaRepository<AccountRequest, Long> {

    /**
     * Query to a get all request by bank
     * @param bic The bic of the Bank we want the request from
     * @return List of request
     */
    @Query(value = "SELECT * FROM ACCOUNT_REQUEST WHERE bic = ?1 AND validator = 0", nativeQuery = true)
    List<AccountRequest> findByBank(String bic);

    /**
     * Query that get all the Request with validator = 1
     * @return A list of Requests to process
     */
    @Query(value = "SELECT * FROM ACCOUNT_REQUEST WHERE validator = 1", nativeQuery = true)
    List<AccountRequest> requestToProcess();

    /**
     * Query to get a specific request by its id
     * @param accRequestID The id of the request we want
     * @return An Optional containing the potential request that correspond to the ID
     */
    @Query(value = "SELECT * FROM ACCOUNT_REQUEST WHERE accRequestID = ?1", nativeQuery = true)
    Optional<AccountRequest> findByAccRequestID(Long accRequestID);
}
