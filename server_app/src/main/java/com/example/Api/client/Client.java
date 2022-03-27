package com.example.Api.client;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity(name = "CLIENTS")
@Table(name = "CLIENTS")
//@IdClass(ClientID.class)
//@SQLInsert(sql = "INSERT IGNORE INTO CLIENTS(bic, userID)" + "VALUES (?, ?)")
public class Client {
    /*
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

     */

    @EmbeddedId()
    private ClientIDEmb clientIDEmb;



}
