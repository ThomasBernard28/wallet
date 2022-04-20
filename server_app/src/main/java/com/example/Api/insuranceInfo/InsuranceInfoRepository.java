package com.example.Api.insuranceInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Insurance Info Repository
 */
@Repository
public interface InsuranceInfoRepository extends JpaRepository<InsuranceInfo, String> {

    /**
     * @param insType The insType we want to get the info from
     * @return An Optional containing a potential InsuranceInfo that correspond to the insType
     */
    @Query(value = "SELECT * FROM INSURANCES_INFO WHERE insType = ?1", nativeQuery = true)
    Optional<InsuranceInfo> findByInsType(String insType);
}
