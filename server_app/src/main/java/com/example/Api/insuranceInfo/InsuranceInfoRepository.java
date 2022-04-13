package com.example.Api.insuranceInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InsuranceInfoRepository extends JpaRepository<InsuranceInfo, String> {

    @Query(value = "SELECT * FROM INSURANCES_INFO WHERE insType = ?1", nativeQuery = true)
    Optional<InsuranceInfo> findByInsType(String insType);
}
