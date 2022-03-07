package com.example.Api.user;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


@Entity(name = "USERS")
@Table(
        name = "USERS",
        uniqueConstraints = @UniqueConstraint(
                name = "users_natid_unique",
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

    @Column(
            name = "language",
            nullable = false,
            columnDefinition = "CHAR(2)"
    )
    private String language;
}
