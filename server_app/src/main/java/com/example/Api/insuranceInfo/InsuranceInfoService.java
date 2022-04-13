package com.example.Api.insuranceInfo;

import com.example.Api.exception.ApiNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InsuranceInfoService {

    private final InsuranceInfoRepository insuranceInfoRepository;

    public InsuranceInfoService(InsuranceInfoRepository insuranceInfoRepository){
        this.insuranceInfoRepository = insuranceInfoRepository;
    }

    public InsuranceInfo getByType(String insType){
        Optional<InsuranceInfo> optionalInsuranceInfo = insuranceInfoRepository.findByInsType(insType);

        if(optionalInsuranceInfo.isEmpty()){
            throw new ApiNotFoundException("Insurance type : " + insType + " doesn't exist");
        }
        return optionalInsuranceInfo.get();
    }
}
