package com.example.Api.client;


import com.example.Api.bank.Bank;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity(name = "clients")
@Table(name = "clients")
@IdClass(ClientVsInstID.class)
public class ClientVsInst {

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
