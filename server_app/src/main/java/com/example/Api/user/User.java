package com.example.Api.user;

import com.example.Api.language.Language;
import com.example.Api.wallet.Wallet;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

/**
 * User entity class, this class defines the USERS entity in the database.
 * In each package there is one entity class that contains all the arguments of the Entity
 * But also its Keys (PK, FK, UK)
 */

//@Entity stands for the Entity in Spring instance
@Entity(name = "USERS")
//@Table stands for the Entity in the database instance
@Table(
        name = "USERS",
        //defines an unique key in the table USERS
        uniqueConstraints = @UniqueConstraint(
                name = "USERS_natID_uindex",
                columnNames = "natID"
        )
)
public class User {

    @Id
    @Column(
            name = "userID",
            updatable = false,
            columnDefinition = "VARCHAR(255)",
            nullable = false
    )
    private String userID;

    @Column(
            name = "natID",
            updatable = false,
            columnDefinition = "CHAR(11)",
            nullable = false
    )
    private String natID;

    @Column(
            name = "psswd",
            columnDefinition = "VARCHAR(255)",
            nullable = false
    )
    private String psswd;

    @Column(
            name = "lastName",
            updatable = false,
            columnDefinition = "VARCHAR(255)",
            nullable = false
    )
    private String lastName;

    @Column(
            name = "firstName",
            updatable = false,
            columnDefinition = "VARCHAR(255)",
            nullable = false
    )
    private String firstName;

    //defines a One To One foreign key relation between the USERS and LANGUAGES tables.
    @OneToOne()
    @JoinColumn(
            name = "language", //the column in this table
            referencedColumnName = "language", //the column referenced in LANGUAGES table
            foreignKey = @ForeignKey(
                    name = "USERS_LANGUAGES_language_fk"
            )
    )
    private Language language;
}
