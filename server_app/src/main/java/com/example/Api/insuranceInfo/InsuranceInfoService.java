package com.example.Api.insuranceInfo;

import com.example.Api.exception.ApiNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * InsuranceInfoService
 */
@Service
public class InsuranceInfoService {

    private final InsuranceInfoRepository insuranceInfoRepository;

    /**
     * Constructor
     * @param insuranceInfoRepository Insurance Info Repository
     */
    public InsuranceInfoService(InsuranceInfoRepository insuranceInfoRepository){
        this.insuranceInfoRepository = insuranceInfoRepository;
    }

    /**
     * Method to get information about an insurance by its type
     * @param insType The researched type
     * @return Information about the insurance
     */
    public InsuranceInfo getByType(String insType){
        Optional<InsuranceInfo> optionalInsuranceInfo = insuranceInfoRepository.findByInsType(insType);

        if(optionalInsuranceInfo.isEmpty()){
            throw new ApiNotFoundException("Insurance type : " + insType + " doesn't exist");
        }
        return optionalInsuranceInfo.get();
    }
}
