package com.example.Api.account;


import com.example.Api.client.Client;
import com.example.Api.wallet.Wallet;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

/**
 * Account entity class
 */
@Table(name = "ACCOUNTS")
@Entity(name = "ACCOUNTS")
public class Account {

    @Id
    @Column(
            name = "iban",
            updatable = false,
            columnDefinition = "CHAR(16)",
            nullable = false
    )
    private String iban;

    @ManyToOne
    @JoinColumn(
            name = "walletID",
            referencedColumnName = "walletID",
            foreignKey = @ForeignKey(
                    name = "ACCOUNTS_WALLETS_walletID_fk"
            ),
            nullable = true
    )
    private Wallet wallet;

    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "bic", referencedColumnName = "bic"),
            @JoinColumn(name = "userID", referencedColumnName = "userID"),
    }, foreignKey = @ForeignKey(name = "ACCOUNTS_CLIENT_VS_INST_bic_userID_fk")
    )
    private Client client;

    @Column(
            name = "type",
            updatable = false,
            columnDefinition = "CHAR(2)",
            nullable = false
    )
    private String type;

    @Column(
            name = "avgBalance",
            nullable = false
    )
    private Float avgBalance;

    @Column(
            name = "localCurr",
            updatable = false,
            columnDefinition = "CHAR(3)",
            nullable = false
    )
    private String localCurr;

    @Column(
            name = "activity",
            columnDefinition = "Integer(1) default '1' ",
            nullable = false
    )
    private Integer activity;

    public Account(String iban, Client client, String type, Float avgBalance, String localCurr, Integer activity) {
        this.iban = iban;
        this.client = client;
        this.type = type;
        this.avgBalance = avgBalance;
        this.localCurr = localCurr;
        this.activity = activity;
    }
}
