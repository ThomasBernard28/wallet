package com.example.Api.insurance;

import com.example.Api.pensionSaving.PensionSaving;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    @Autowired
    public InsuranceService(InsuranceRepository insuranceRepository){
        this.insuranceRepository = insuranceRepository;
    }

    public void createInsuranceFromPension(PensionSaving pensionSaving){
        Insurance insurance = new Insurance(pensionSaving.getPensionID(), pensionSaving.getWalletID(), pensionSaving.getBic(),
                pensionSaving.getUserID(), pensionSaving.getType(), pensionSaving.getSubDate(), pensionSaving.getRenewDate(), pensionSaving.getActivity());

        insuranceRepository.save(insurance);
    }
}
