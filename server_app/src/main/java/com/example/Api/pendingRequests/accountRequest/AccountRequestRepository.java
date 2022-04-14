package com.example.Api.pendingRequests.accountRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AccountRequestRepository extends JpaRepository<AccountRequest, Long> {


    @Query(value = "SELECT * FROM ACCOUNT_REQUEST WHERE bic = ?1 AND validator = 0", nativeQuery = true)
    List<AccountRequest> findByBank(String bic);

    @Query(value = "SELECT * FROM ACCOUNT_REQUEST WHERE validator = 1", nativeQuery = true)
    List<AccountRequest> requestToProcess();

    @Query(value = "SELECT * FROM ACCOUNT_REQUEST WHERE accRequestID = ?1", nativeQuery = true)
    Optional<AccountRequest> findByAccRequestID(Long accRequestID);
}
