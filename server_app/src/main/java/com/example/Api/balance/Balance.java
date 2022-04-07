package com.example.Api.balance;


import com.example.Api.account.Account;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity(name = "CASH_BALANCES")
@Table(
        name = "CASH_BALANCES",
        uniqueConstraints = @UniqueConstraint(
                name = "CASH_BALANCES_iban_currency_uindex",
                columnNames = {"iban" , "currency"}
        )
)
public class Balance{
    @Id
    @SequenceGenerator(
            name = "balance_sequence",
            sequenceName = "balance_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "balance_sequence"
    )
    @Column(
            name = "balanceID",
            nullable = false,
            updatable = false
    )
    private Long balanceID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "iban",
            nullable = false,
            referencedColumnName = "iban",
            foreignKey = @ForeignKey(
                    name = "CASH_BALANCES_ACCOUTNS_iban_fk"
            )
    )
    private Account iban;

    @Column(
            name = "currency",
            nullable = false,
            updatable = false,
            columnDefinition = "CHAR(3)"
    )
    private String currency;

    @Column(
            name = "balance",
            nullable = false
    )
    private Float balance;

    public Balance(Account iban, String currency, Float balance) {
        this.iban = iban;
        this.currency = currency;
        this.balance = balance;
    }
}
