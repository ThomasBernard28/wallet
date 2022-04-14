package com.example.Api.bankIdentifiers;

import com.example.Api.bank.Bank;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


@Entity(name = "BANK_IDENTiFIER")
@Table(name = "BANK_IDENTIFIER")
public class BankIdentifier {

    @Id
    @Column(
            name = "identifier",
            nullable = false,
            updatable = false,
            columnDefinition = "Integer(3)"
    )
    private Integer identifier;

    @OneToOne
    @JoinColumn(
            name = "bic",
            referencedColumnName = "bic",
            foreignKey = @ForeignKey(
                    name = "BANK_IDENTIFIER_INSTITUTIONS_bic_fk"
            )
    )
    private Bank bank;

    @Column(
            name = "prefix",
            nullable = false,
            updatable = false,
            columnDefinition = "CHAR(4)"
    )
    private String prefix;

    @Column(
            name = "lastIban",
            nullable = false,
            columnDefinition = "Integer(9)"
    )
    private Integer lastIban;


}
