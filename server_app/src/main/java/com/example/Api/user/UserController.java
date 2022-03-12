package com.example.Api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
    public void registerNewUser(@RequestBody User user){

        userService.addNewUser(user);
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