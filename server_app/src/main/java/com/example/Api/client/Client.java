package com.example.Api.client;


import com.example.Api.bank.Bank;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity(name = "CLIENT")
@Table(name = "CLIENTS")
@IdClass(ClientID.class)
public class Client {

    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bic", referencedColumnName = "bic")
    private Bank bank;

    @Id
    @Column(
            name = "userID",
            nullable = false,
            updatable = false
    )
    private String userID;

}
