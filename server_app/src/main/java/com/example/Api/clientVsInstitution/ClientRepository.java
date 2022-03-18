package com.example.Api.clientVsInstitution;

import com.example.Api.institution.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, ClientId> {

    @Query("SELECT s FROM CLIENT_VS_INST s WHERE s.institution.bic = ?1")
    List<Client> findClientByInstitution(String bic);

    @Query("SELECT s FROM CLIENT_VS_INST s WHERE s.institution = ?1 and s.userID=?2")
    Optional<Client> getClientByInstitutionAndAndUserID(Institution institution, String userID);
}
