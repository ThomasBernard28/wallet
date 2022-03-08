package com.example.Api.wallet;

import com.example.Api.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


@Entity(name = "WALLETS")
@Table(
        name = "WALLETS",
        uniqueConstraints = @UniqueConstraint(
                name = "WALLETS_userID_bic_uindex",
                columnNames = {"userID", "bic"}
        )
)
public class Wallet{
    @Id
    @SequenceGenerator(
            name = "wallet_sequence",
            sequenceName = "wallet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "wallet_sequence"
    )
    @Column(
            name = "walletID",
            nullable = false,
            updatable = false
    )
    private Long walletID;
    /*
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "userID",
            columnDefinition = "userID"
    )*/
    @Column(
            name = "userID",
            nullable = false,
            updatable = false
    )
    private String userID;

    @Column(
            name = "bic",
            nullable = false,
            updatable = false,
            columnDefinition = "CHAR(8)"
    )
    private String bic;

    @Column(
            name = "openingDate",
            nullable = false,
            updatable = false
    )
    private LocalDate openingDate;

    @Column(
            name = "activity",
            nullable = false
    )
    private Integer activity;

}
