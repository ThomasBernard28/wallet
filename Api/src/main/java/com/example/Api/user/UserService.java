package com.example.Api.user;

import com.example.Api.language.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getOneUser(String userID){
        Optional<User> userById = userRepository.findUserByUserID(userID);
        boolean isPresent = userById.isPresent();
        if(!isPresent){
            throw new IllegalStateException("This user doesn't exist");
        }
        return userRepository.findUserByUserID(userID);
    }


    public void addNewUser(User user){
        Optional<User> userOptional = userRepository.findUserByUserID(user.getUserID());

        if(userOptional.isPresent()){
            throw new IllegalStateException("User already exists");
        }
        userRepository.save(user);
    }

    public void deleteUser(String userID){
        boolean exists = userRepository.existsById(userID);

        if(!exists){
            throw new IllegalStateException("The user doesn't exist with id : " + userID);
        }

        userRepository.deleteById(userID);
    }

    @Transactional
    public void updateUser(String userID, String psswd, String language){
        User user = userRepository.findById(userID).orElseThrow(() -> new IllegalStateException(
                "user with id " + userID + "doesn't exist"
        ));

        if(psswd != null && psswd.length() > 0 && !Objects.equals(user.getPsswd(), psswd)){
            user.setPsswd(psswd);
        }

        if(language != null && language.length() == 2 && !Objects.equals(user.getLanguage(), new Language(language))){
           user.setLanguage(new Language(language));
        }
    }
}
