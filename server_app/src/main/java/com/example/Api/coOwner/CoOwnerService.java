package com.example.Api.coOwner;

import com.example.Api.client.Client;
import com.example.Api.client.ClientRepository;
import com.example.Api.exception.ApiIncorrectException;
import com.example.Api.exception.ApiNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * CoOwner Service
 */
@Service
public class CoOwnerService {

    private final CoOwnerRepository coOwnerRepository;
    private final ClientRepository clientRepository;

    /**
     * Constructor
     * @param coOwnerRepository CoOwner Repository
     * @param clientRepository Client Repository
     */
    @Autowired
    public CoOwnerService(CoOwnerRepository coOwnerRepository, ClientRepository clientRepository){
        this.coOwnerRepository = coOwnerRepository;
        this.clientRepository = clientRepository;
    }

    /**
     *Method to get all the coOwners of an Account
     * @param iban The iban of the owner Account
     * @return A List containing all teh coOwners of an acocunt
     */
    public List<CoOwner> getAllByIbanOwner(String iban){
        return coOwnerRepository.findByIbanOwner(iban);
    }

    /**
     * Method to register a new CoOwner for an Account
     * @param walletID The walletID of the coOwner
     * @param ibanOwner The iban of the owner Account
     * @param userID_coOwner The useriD of the coOwner User
     * @param bicOwner The bic of the bank of the owner
     * @param userIDOwner the userID of the Owner User
     */
    public void registerCoOwner(Long walletID, String ibanOwner, String userID_coOwner, String bicOwner, String userIDOwner){
        //Find the client
        Client client  = clientRepository.findByBankAndUserID(bicOwner, userIDOwner).get();
        //Search if this user is not already a coOwner of this account
        Optional<CoOwner> optionalCoOwner = coOwnerRepository.findByWalletIDAndIban(walletID, ibanOwner);

        //If so throw an exception
        if (optionalCoOwner.isPresent()){
            throw new ApiIncorrectException("This client : "+ userID_coOwner + " is already co_owner of the account " + ibanOwner + " for the wallet : " + walletID);
        }
        //Else create the new coOwner
        CoOwner coOwner = new CoOwner(new CoOwnerID(walletID, ibanOwner, userID_coOwner), client);

        //save it
        coOwnerRepository.save(coOwner);
    }

    /**
     * Method to delete a coOwner of an Account
     * @param walletID_coOwner the walletID of the coOwner's wallet
     * @param ibanOwner The iban of the owner Account
     */
    public void deleteCoOwner(Long walletID_coOwner, String ibanOwner){
        //First check if there is a coOwner with this information
        Optional<CoOwner> optionalCoOwner = coOwnerRepository.findByWalletIDAndIban(walletID_coOwner, ibanOwner);

        //If not throw an exception
        if(optionalCoOwner.isEmpty()){
            throw new ApiNotFoundException("The coOwner doesn't exist");
        }
        //Else delete
        coOwnerRepository.delete(optionalCoOwner.get());
    }
}
