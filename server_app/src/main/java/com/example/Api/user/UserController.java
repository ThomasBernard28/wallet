package com.example.Api.user;

import com.example.Api.exception.ApiNotFoundException;
import com.example.Api.language.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This class is a REST controller. This class is supposed to be the end-point of the incoming
 * HTTP request. In the @RequestMapping we define the path that has to be specified in the URI.
 */

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;
    private final LanguageService languageService;

    /**
     * Constructor of the Controller
     * @param userService The user service where methods are implemented to interract between the HTTP layer and DB
     * @param languageService The language service same objective as userService
     */
    @Autowired
    public UserController(UserService userService, LanguageService languageService) {
        this.userService = userService;
        this.languageService = languageService;
    }

    /**
     * This method calls the userService to get all the registered users of the app
     * @return All the users that are in the DB
     */
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    /**
     * This method gets the userID from the URI to research only one user
     * @param userID the userID we want the data from
     * @return User instance with all the data
     */
    @GetMapping(path = "{userId}")
    public User getOneUser(@PathVariable("userId") String userID){

        return userService.getOneUser(userID).get();
    }

    /**
     * This method is a POST its objective is to register a new User in the DB
     * This method translate the Json body to data that can be used in the userService
     * @param json A Mapping of the incoming json
     */
    @PostMapping
    public void registerNewUser(@RequestBody Map<String, String> json){
        Optional<User> userOptional = userService.getOneUser(json.get("userID"));

        if(userOptional.isPresent()){
            throw new ApiNotFoundException("This user already exists");
        }
        userService.addNewUser(new User(json.get("userID"), json.get("natID"), json.get("psswd"), json.get("lastName"),
                json.get("firstName"), languageService.getLanguage(json.get("language"))));
    }

    /**
     * This method delete the User in the DB with the userID that it gets from the URI
     * @param userID The User that we want to delete
     */
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId")String userID){
        userService.deleteUser(userID);
    }

    /**
     * Method to
     * @param userID
     * @param json
     */
    @PutMapping(path = "psswd/{userId}")
    public void updateUserPsswd(
            @PathVariable("userId") String userID,
            @RequestBody Map<String, String> json){

        userService.updateUserPassword(userID, json.get("psswd"));
    }

    @PutMapping(path = "language/{userId}")
    public void updateUserLanguage(
            @PathVariable("userId") String userID,
            @RequestBody Map<String, String> json
    ){
        userService.updateUserLanguage(userID, json.get("language"));
    }
}
