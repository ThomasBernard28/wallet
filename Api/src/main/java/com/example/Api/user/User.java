package com.example.Api.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "USERS")
public class User {

    @Id
    private String userID;
    private String natID;
    private String psswd;
    private String lastName;
    private String firstName;
    private String language;

}
