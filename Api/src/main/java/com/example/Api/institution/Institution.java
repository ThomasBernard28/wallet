package com.example.Api.institution;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity(name = "INSTITUTIONS")
@Table(name = "INSTITUTIONS")
public class Institution {

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

    @Column(
            name = "language",
            nullable = false,
            columnDefinition = "CHAR(2)"
    )
    private String language;
}
