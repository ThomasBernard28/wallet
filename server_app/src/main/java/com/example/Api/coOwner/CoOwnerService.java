package com.example.Api.coOwner;

import com.example.Api.client.Client;
import com.example.Api.client.ClientRepository;
import com.example.Api.exception.ApiIncorrectException;
import com.example.Api.exception.ApiNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CoOwnerService {

    private final CoOwnerRepository coOwnerRepository;
    private final ClientRepository clientRepository;


    @Autowired
    public CoOwnerService(CoOwnerRepository coOwnerRepository, ClientRepository clientRepository){
        this.coOwnerRepository = coOwnerRepository;
        this.clientRepository = clientRepository;
    }

    public List<CoOwner> getAllByIbanOwner(String iban){
        return coOwnerRepository.findByIbanOwner(iban);
    }

    public void registerCoOwner(Long walletID, String ibanOwner, String userID_coOwner, String bicOwner, String userIDOwner){
        Client client  = clientRepository.findByBankAndUserID(bicOwner, userIDOwner).get();
        Optional<CoOwner> optionalCoOwner = coOwnerRepository.findByWalletIDAndIban(walletID, ibanOwner);

        if (optionalCoOwner.isPresent()){
            throw new ApiIncorrectException("This client : "+ userID_coOwner + " is already co_owner of the account " + ibanOwner + " for the wallet : " + walletID);
        }
        CoOwner coOwner = new CoOwner(new CoOwnerID(walletID, ibanOwner, userID_coOwner), client);

        coOwnerRepository.save(coOwner);
    }

    public void deleteCoOwner(Long walletID_coOwner, String ibanOwner){
        Optional<CoOwner> optionalCoOwner = coOwnerRepository.findByWalletIDAndIban(walletID_coOwner, ibanOwner);

        if(!optionalCoOwner.isPresent()){
            throw new ApiNotFoundException("The coOwner doesn't exist");
        }
        coOwnerRepository.delete(optionalCoOwner.get());
    }
}
