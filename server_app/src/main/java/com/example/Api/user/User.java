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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "language", //the column in this table
            referencedColumnName = "language", //the column referenced in LANGUAGES table
            foreignKey = @ForeignKey(
                    name = "USERS_LANGUAGES_language_fk"
            )
    )
    private Language language;

    @OneToMany(
            mappedBy = "walletID",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<Wallet> wallets = new ArrayList<>();

    public void addWallet(Wallet wallet){
        if (!this.wallets.contains(wallet)){
            this.wallets.add(wallet);
            wallet.setUser(this);
        }
    }

    public void removeWallet(Wallet wallet){
        if(this.wallets.contains(wallet)){
            this.wallets.remove(wallet);
            wallet.setUser(null);
        }
    }

    public User(String userID, String natID, String psswd, String lastName, String firstName, Language language ){
        this.userID = userID;
        this.natID = natID;
        this.psswd = psswd;
        this.lastName = lastName;
        this.firstName = firstName;
        this.language = language;
    }
}
