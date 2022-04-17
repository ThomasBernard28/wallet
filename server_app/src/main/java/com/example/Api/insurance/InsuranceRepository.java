package com.example.Api.insurance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    @Query(value = "SELECT * FROM INSURANCES WHERE walletID = ?1 AND activity = 1", nativeQuery = true)
    List<Insurance> findAllActiveByWalletID(Long walletID);

    @Query(value = "SELECT * FROM INSURANCES WHERE walletID = ?1", nativeQuery = true)
    List<Insurance> findAllByWalletID(Long walletID);

    @Query(value = "SELECT * FROM INSURANCES WHERE bic = ?1 and userID = ?2", nativeQuery = true)
    List<Insurance> findAllByBicAndUserID(String bic, String userID);

    @Query(value = "SELECT * FROM INSURANCES WHERE insID = ?1", nativeQuery = true)
    Optional<Insurance> findByInsID(Long insID);
}
