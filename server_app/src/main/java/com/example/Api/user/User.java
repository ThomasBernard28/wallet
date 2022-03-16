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


@Entity(name = "USERS")
@Table(
        name = "USERS",
        uniqueConstraints = @UniqueConstraint(
                name = "USERS_natID_uindex",
                columnNames = "natID"
        )
)
public class User {

    @Id
    @Column(
            name = "userID",
            nullable = false,
            updatable = false
    )
    private String userID;

    @Column(
            name = "natID",
            nullable = false,
            updatable = false,
            columnDefinition = "CHAR(11)"
    )
    private String natID;

    @Column(
            name = "psswd",
            nullable = false
    )
    private String psswd;

    @Column(
            name = "lastName",
            nullable = false,
            updatable = false
    )
    private String lastName;

    @Column(
            name = "firstName",
            nullable = false,
            updatable = false
    )
    private String firstName;

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
