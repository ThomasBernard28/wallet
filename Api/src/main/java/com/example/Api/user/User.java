package com.example.Api.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User {

    private String userID;
    private String natID;
    private String psswd;
    private String lastName;
    private String firstName;
    private String language;

}
