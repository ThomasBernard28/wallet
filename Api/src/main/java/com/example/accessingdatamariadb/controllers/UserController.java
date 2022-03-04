package com.example.accessingdatamariadb.controllers;

import com.example.accessingdatamariadb.repositories.UserRepository;
import com.example.accessingdatamariadb.data.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//This code come from :https://spring.io/guides/gs/accessing-data-mysql/
@RequiredArgsConstructor
@Controller // This means that this class is a Controller
@RequestMapping(path="/Api") //URL's start with /Api (after application path)
public class UserController {


    private final UserRepository userRepository;

    @PostMapping(path="/add") //Map only POST request
    public @ResponseBody String addNewUser (@RequestParam String userID, @RequestParam Integer natID,
                                            @RequestParam String password, @RequestParam String lastName,
                                            @RequestParam String firstName, @RequestParam String language){

        //@ResponseBody means the returned String is the response, not a view name
        //@RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setUserID(userID);
        n.setNatID(natID);
        n.setPassword(password);
        n.setLastName(lastName);
        n.setFirstName(firstName);
        n.setLanguage(language);

        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        // This returns a JSON with the Users
        return userRepository.findAll();
    }


}
