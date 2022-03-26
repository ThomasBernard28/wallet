package com.example.Api.user;

import com.example.Api.language.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;
    private final LanguageService languageService;

    @Autowired
    public UserController(UserService userService, LanguageService languageService) {
        this.userService = userService;
        this.languageService = languageService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(path = "{userId}")
    public User getOneUser(@PathVariable("userId") String userID){

        return userService.getOneUser(userID).get();
    }


    @PostMapping
    public void registerNewUser(@RequestBody Map<String, String> json){
        Optional<User> userOptional = userService.getOneUser(json.get("userID"));

        if(userOptional.isPresent()){
            throw new IllegalStateException("This user already exists");
        }
        userService.addNewUser(new User(json.get("userID"), json.get("natID"), json.get("psswd"), json.get("lastName"),
                json.get("firstName"), languageService.getLanguage(json.get("language"))));
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId")String userID){
        userService.deleteUser(userID);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(
            @PathVariable("userId") String userID,
            @RequestParam(required = false) String psswd,
            @RequestParam(required = false) String language){

        userService.updateUser(userID, psswd, language);
    }
}
