package com.example.Api.coOwner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface coOwnerRepository extends JpaRepository<CoOwner, CoOwnerID> {

    @Query("SELECT s FROM CO_OWNER s WHERE s.coOwnerID.ibanOwner = ?1")
    List<CoOwner> findByIbanOwner(String ibanOwner);

    @Query("SELECT s FROM CO_OWNER s WHERE s.coOwnerID.walletID = ?1")
    List<CoOwner> findByCoOwner(String walletID);
}
