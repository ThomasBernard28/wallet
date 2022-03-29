package com.example.Api.bank;

import com.example.Api.client.Client;
import com.example.Api.language.Language;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity(name = "INSTITUTIONS")
@Table(name = "INSTITUTIONS")
public class Bank {

    @Id
    @Column(
            name = "bic",
            nullable = false,
            updatable = false,
            columnDefinition = "CHAR(8)"
    )
    private String bic;

    @Column(
            name = "psswd",
            nullable = false
    )
    private String psswd;

    @Column(
            name = "name",
            nullable = false,
            updatable = false
    )
    private String name;

    @OneToOne
    @JoinColumn(
            name = "language",
            referencedColumnName = "language",
            foreignKey = @ForeignKey(
                    name = "INSTITUTIONS_LANGUAGES_language_fk"
            )

    )
    private Language language;
}
