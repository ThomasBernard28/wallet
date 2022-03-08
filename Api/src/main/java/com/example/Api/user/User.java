package com.example.Api.user;

import com.example.Api.language.Language;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "language", //the column in this table
            referencedColumnName = "language" //the column referenced in LANGUAGES table
    )
    private Language language;

}
