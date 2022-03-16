package com.example.Api.clientVsInstitution;

import com.example.Api.institution.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Institution> {

    List<Client> findClientByInstitution(Institution institution);

    Client getClientByInstitutionAndAndUserID(Institution institution, String userID);
}
