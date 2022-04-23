package com.example.Api.wallet;

import com.example.Api.account.Account;
import com.example.Api.user.User;
import lombok.*;
import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

/**
 * WALLETS Entity class
 */
@Entity(name = "WALLETS")
@Table(
        name = "WALLETS",
        //defining an UK in the table
        uniqueConstraints = @UniqueConstraint(
                name = "WALLETS_userID_bic_uindex",
                columnNames = {"userID", "bic"}
        )
)
public class Wallet{
    @Id
    //With Sequence Generator we are able to automatically generate a unique walletID
    @SequenceGenerator(
            name = "wallet_sequence",
            sequenceName = "wallet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
        strategy = SEQUENCE,
        generator = "wallet_sequence"
    )
    @Column(
            name = "walletID",
            updatable = false,
            columnDefinition = "BIGINT(255)",
            nullable = false
    )
    private Long walletID;

    //Many to One foreign key relation
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "userID",
            referencedColumnName = "userID",
            foreignKey = @ForeignKey(
                    name = "WALLETS_USERS_userID_fk"
            ),
            nullable = false
    )
    private User user;

    @Column(
            name = "bic",
            updatable = false,
            columnDefinition = "CHAR(8)",
            nullable = false
    )
    private String bic;

    @Column(
            name = "openingDate",
            updatable = false,
            columnDefinition = "DATE",
            nullable = false
    )
    private LocalDate openingDate;

    @Column(
            name = "activity",
            columnDefinition = "Integer(1) default '1'",
            nullable = false
    )
    private Integer activity;

    public Wallet(User user, String bic, LocalDate openingDate, Integer activity){
        this.user = user;
        this.bic=bic;
        this.openingDate = openingDate;
        this.activity = activity;
    }
}
