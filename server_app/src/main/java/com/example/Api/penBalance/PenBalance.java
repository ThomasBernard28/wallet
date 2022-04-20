package com.example.Api.penBalance;

import com.example.Api.pensionSaving.PensionSaving;
import lombok.*;


import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

/**
 * Pension Balance entity
 */
@Entity(name = "PENSION_BALANCE")
@Table(name = "PENSION_BALANCE")
public class PenBalance {

    @Id
    @SequenceGenerator(
            name = "penBalance_sequence",
            sequenceName = "penBalance_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "penBalance_sequence",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "penBalanceID",
            nullable = false,
            updatable = false
    )
    private Long insBalanceID;

    @OneToOne
    @JoinColumn(
            name = "pensionID",
            referencedColumnName = "pensionID",
            foreignKey = @ForeignKey(
                    name = "PENSION_BALANCE_PENSION_SAVINGS_pensionId_fk"
            )
    )
    private PensionSaving pensionSaving;


    @Column(
            name = "balance",
            nullable = false
    )
    private Float balance;

    @Column(
            name = "currency",
            nullable = false,
            updatable = false,
            columnDefinition = "CHAR(3) default 'EUR'"
    )
    private String currency;

    public PenBalance(PensionSaving pensionSaving, Float balance, String currency) {
        this.pensionSaving = pensionSaving;
        this.balance = balance;
        this.currency = currency;
    }
}
