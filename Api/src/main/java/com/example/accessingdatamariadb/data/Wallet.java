package com.example.accessingdatamariadb.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import static javax.persistence.GenerationType.SEQUENCE;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Wallet {

    @Id
    @SequenceGenerator(
            name = "wallet_sequence",
            sequenceName = "wallet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "wallet_sequence"

    )
    private Integer walletID;
    private String userID;
    private String bic;
    private Integer openningDate;
    private Integer activity;
}
