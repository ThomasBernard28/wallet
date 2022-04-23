package com.example.Api.coOwner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * CoOwner Repository
 */
@Repository
public interface CoOwnerRepository extends JpaRepository<CoOwner, CoOwnerID> {

    /**
     * Query to get all the coOwner of an account
     * @param ibanOwner The iban of the account
     * @return A List containing all the coOwners of the Account
     */
    @Query(value = "SELECT * FROM CO_OWNER WHERE ibanOwner = ?1", nativeQuery = true)
    List<CoOwner> findByIbanOwner(String ibanOwner);

    /**
     * Query to get a CoOwner by its walletID and the Iban of the owner account
     * @param walletID The walletID of the coOwner Wallet
     * @param ibanOwner The iban of the owner Account
     * @return An Optional containing the potential CoOwner.
     */
    @Query("SELECT s FROM CO_OWNER s WHERE s.coOwnerID.walletID = ?1 AND s.coOwnerID.ibanOwner = ?2")
    Optional<CoOwner> findByWalletIDAndIban(Long walletID, String ibanOwner);


}
