package com.example.Api.insuranceInfo;

import com.example.Api.pensionSaving.PensionSaving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PensionSavingRepository extends JpaRepository<PensionSaving, Long> {


}
