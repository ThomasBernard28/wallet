package com.example.Api.pensionSaving;

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

@Entity
@Table(name = "PENSION_SAVINGS")
public class PensionSaving {

    @Id
    @SequenceGenerator(
            name = "pension_sequence",
            sequenceName = "pension_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pension_sequence"
    )
    @Column(
            name = "pensionID",
            nullable = false,
            updatable = false
    )
    private Long pensionID;

    @OneToOne
    @JoinColumn(
            name = "walletID",
            nullable = false,
            referencedColumnName = "walletID",
            foreignKey = @ForeignKey(
                    name = "PENSION_SAVINGS_WALLETS_walletID_fk"
            )
    )
    private Wallet walletID;

    @OneToOne
    @JoinColumns(value = {
            @JoinColumn(name = "bic", referencedColumnName = "bic"),
            @JoinColumn(name = "userID", referencedColumnName = "userID"),},
            foreignKey = @ForeignKey(name = "PENSION_SAVINGS_CLIENTS_bic_userID_fk"))
    private Client client;

    @Column(
            name = "category",
            nullable = false,
            columnDefinition = "Integer(2) default '30' "
    )
    private Integer category;

    @Column(
            name = "subdate",
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
            columnDefinition = "Integer(1) default '0' "
    )
    private Integer activity;

}
