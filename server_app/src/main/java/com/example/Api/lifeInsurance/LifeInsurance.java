package com.example.Api.lifeInsurance;

import com.example.Api.client.Client;
import com.example.Api.wallet.Wallet;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity(name = "LIFE_INSURANCES")
@Table(name = "LIFE_INSURANCES")
public class LifeInsurance {

    @Id
    @SequenceGenerator(
            name = "life_sequence",
            sequenceName = "life_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "life_sequence"
    )
    @Column(
            name = "lifeID",
            nullable = false,
            updatable = false
    )
    private Long lifeID;

    @OneToOne
    @JoinColumn(
            name = "walletID",
            referencedColumnName = "walletID",
            foreignKey = @ForeignKey(
                    name = "LIFE_INSURANCES_WALLETS_walletID_fk"
            )
    )
    private Wallet walletID;

    @OneToOne
    @JoinColumns(value =
            {
                    @JoinColumn(name = "bic", referencedColumnName = "bic"),
                    @JoinColumn(name = "userID", referencedColumnName = "userID")
            }, foreignKey = @ForeignKey(name = "LIFE_INSURANCES_CLIENTS_bic_userID_fk"))
    private Client client;

    @Column(
            name = "percentage",
            nullable = false
    )
    private Float percentage;

    @Column(
            name = "subDate",
            nullable = false
    )
    private LocalDate subDate;

    @Column(
            name = "balance",
            nullable = false
    )
    private Float balance;

    @Column(
            name = "activity",
            nullable = false,
            columnDefinition = "Integer(1) default '1' "
    )
    private Integer activity;
}
