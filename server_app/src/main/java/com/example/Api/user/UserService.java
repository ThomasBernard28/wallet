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

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;

    @Autowired
    public UserService(UserRepository userRepository, LanguageRepository languageRepository){
        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getOneUser(String userID){

        return userRepository.findUserByUserID(userID);
    }


    public void addNewUser(User user){
        Optional<User> userOptional = userRepository.findUserByUserID(user.getUserID());

        if(userOptional.isPresent()){
            throw new ApiIncorrectException("User with userID : " +  user.getUserID() + " already exists");
        }
        userRepository.save(user);
    }

    public void deleteUser(String userID){
        Optional<User> userOptional = userRepository.findById(userID);


        if(userOptional.isEmpty()){
            throw new ApiNotFoundException("The user doesn't exist with id : " + userID);
        }

        userRepository.delete(userOptional.get());
    }

    @Transactional
    public void updateUserPassword(String userID, String psswd){
        User user = userRepository.findById(userID).orElseThrow(() -> new ApiNotFoundException(
                "user with id " + userID + "doesn't exist"
        ));

        if(psswd != null && psswd.length() > 0 && !Objects.equals(user.getPsswd(), psswd)){
            user.setPsswd(psswd);
        }

    }

    @Transactional
    public void updateUserLanguage(String userID, String language){
        User user = userRepository.findById(userID).orElseThrow(() -> new ApiNotFoundException(
                "user with id " + userID + "doesn't exist"
        ));
        Optional<Language> optionalLanguage = languageRepository.findLanguageByLanguage(language);

        if(!optionalLanguage.isPresent()){
            throw new ApiNotFoundException("Language : " + language+ " does not exist");
        }

        if(language != null && language.length() == 2 && !Objects.equals(user.getLanguage(), optionalLanguage.get())){
            user.setLanguage(optionalLanguage.get());
        }
    }
}
