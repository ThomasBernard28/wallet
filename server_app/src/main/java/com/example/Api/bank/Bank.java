package com.example.Api.bank;

import com.example.Api.client.Client;
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
}
