package com.example.Api.insurance;

import com.example.Api.client.Client;
import com.example.Api.client.ClientRepository;
import com.example.Api.exception.ApiNotFoundException;
import com.example.Api.pensionSaving.PensionSaving;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public InsuranceService(InsuranceRepository insuranceRepository, ClientRepository clientRepository){
        this.insuranceRepository = insuranceRepository;
        this.clientRepository = clientRepository;
    }

    public List<Insurance> getAllByWalletID(Long walletID){
        return insuranceRepository.findAllActiveByWalletID(walletID);
    }

    public List<Insurance> getAllByClient(String bic, String userID){
        Optional<Client> optionalClient = clientRepository.findByBankAndUserID(bic, userID);

        if(optionalClient.isEmpty()){
            throw new ApiNotFoundException("Client with id : " + userID + " in the bank : " + bic + " does not exist");
        }

        return insuranceRepository.findAllByBicAndUserID(bic, userID);
    }

    public List<Insurance> getAllWithInactive(Long walletID){
        return insuranceRepository.findAllByWalletID(walletID);
    }

    public void createInsuranceFromPension(PensionSaving pensionSaving){
        Insurance insurance = new Insurance(pensionSaving.getPensionID(), pensionSaving.getWalletID(), pensionSaving.getBic(),
                pensionSaving.getUserID(), pensionSaving.getType(), pensionSaving.getSubDate(), pensionSaving.getRenewDate(), pensionSaving.getActivity());

        insuranceRepository.save(insurance);
    }
}
