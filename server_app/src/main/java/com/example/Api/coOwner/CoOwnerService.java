package com.example.Api.coOwner;

import com.example.Api.client.Client;
import com.example.Api.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public void registerCoOwner(Long walletID, String ibanOwner, String userID_coOwner, String bicOwner, String userIDOwner) throws IllegalStateException{
        Client client  = clientRepository.findByBankAndUserID(bicOwner, userIDOwner).get();
        Optional<CoOwner> optionalCoOwner = coOwnerRepository.findByWalletIDAndIban(walletID, ibanOwner);

        if (optionalCoOwner.isPresent()){
            throw new IllegalStateException("This client is already co_owner");
        }
        CoOwner coOwner = new CoOwner(new CoOwnerID(walletID, ibanOwner, userID_coOwner), client);

        coOwnerRepository.save(coOwner);
    }
}
