package com.example.Api.user;

import com.example.Api.exception.ApiIncorrectException;
import com.example.Api.exception.ApiNotFoundException;
import com.example.Api.language.Language;
import com.example.Api.language.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The Service is the intermediate layer of the API. In the service we can find the methods
 * that will convert data recovered from the Controller (HTTP Request) to Queries in the Repository.
 * Then it will return response from the Repository to the Controller.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;

    /**
     * This is the constructor of the class taking repository that will be necessary to
     * pick up data from the db.
     * @param userRepository
     * @param languageRepository
     */
    @Autowired
    public UserService(UserRepository userRepository, LanguageRepository languageRepository){
        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    /**
     * This method get one user by his ID
     * @param userID the ID of the user we want to get
     * @return it returns an Optional which a kind of Collection on which we can check
     * if the user is present (.isPresent()) or if the Optional is empty (.isEmpty())
     */
    public Optional<User> getOneUser(String userID){

        return userRepository.findUserByUserID(userID);
    }


    public void addNewUser(User user){
        // First we check if the user exists
        Optional<User> userOptional = userRepository.findUserByUserID(user.getUserID());

        if(userOptional.isPresent()){
            //If not we throw a custom exception
            throw new ApiIncorrectException("User with userID : " +  user.getUserID() + " already exists");
        }
        //else we save it using the repository
        //save method works like an INSERT SQL Request
        userRepository.save(user);
    }

    public void deleteUser(String userID){
        Optional<User> userOptional = userRepository.findById(userID);


        if(userOptional.isEmpty()){
            throw new ApiNotFoundException("The user doesn't exist with id : " + userID);
        }

        userRepository.delete(userOptional.get());
    }
    //Transactional is here to revert changes in case of errors that may occur during the process
    @Transactional
    public void updateUserPassword(String userID, String psswd){
        //Using a .orElseThrow to throw custom exception in case we don't find user.
        //It works like a try catch but inside the method
        User user = userRepository.findById(userID).orElseThrow(() -> new ApiNotFoundException(
                "user with id " + userID + "doesn't exist"
        ));

        //We don't want the password to be the same as the previous one
        if(psswd != null && psswd.length() > 0 && !Objects.equals(user.getPsswd(), psswd)){
            user.setPsswd(psswd);
        }

    }

    @Transactional
    public void updateUserLanguage(String userID, String language){
        User user = userRepository.findById(userID).orElseThrow(() -> new ApiNotFoundException(
                "user with id " + userID + "doesn't exist"
        ));
        //We need to check if the language is supported by the application
        Optional<Language> optionalLanguage = languageRepository.findLanguageByLanguage(language);

        if(!optionalLanguage.isPresent()){
            //if not we throw an exception saying that the language wasn't found.
            throw new ApiNotFoundException("Language : " + language+ " does not exist");
        }

        // We don't want the language to be the same as the previous one
        if(language != null && language.length() == 2 && !Objects.equals(user.getLanguage(), optionalLanguage.get())){
            user.setLanguage(optionalLanguage.get());
        }
    }
}
