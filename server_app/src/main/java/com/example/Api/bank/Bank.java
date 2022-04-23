package com.example.Api.bank;

import com.example.Api.language.Language;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

/**
 * Bank Entity class
 */
@Entity(name = "INSTITUTIONS")
@Table(name = "INSTITUTIONS")
public class Bank {

    @Id
    @Column(
            name = "bic",
            updatable = false,
            columnDefinition = "CHAR(8)",
            nullable = false
    )
    private String bic;

    @Column(
            name = "psswd",
            nullable = false
    )
    private String psswd;

    @Column(
            name = "name",
            updatable = false,
            nullable = false
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
