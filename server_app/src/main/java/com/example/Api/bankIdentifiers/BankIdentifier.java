package com.example.Api.bankIdentifiers;

import com.example.Api.bank.Bank;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

/**
 * Bank Identifier Entity, contains all the informations about the bank
 * The parameters to create an iban as the bank code and the latest iban generated
 */
@Entity(name = "BANK_IDENTiFIER")
@Table(name = "BANK_IDENTIFIER")
public class BankIdentifier {

    @Id
    @Column(
            name = "identifier",
            updatable = false,
            columnDefinition = "Integer(3)",
            nullable = false
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
            updatable = false,
            columnDefinition = "CHAR(4)",
            nullable = false
    )
    private String prefix;

    @Column(
            name = "lastIban",
            columnDefinition = "Integer(9)",
            nullable = false
    )
    private Integer lastIban;


}
