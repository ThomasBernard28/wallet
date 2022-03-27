package com.example.Api.client;

import com.example.Api.bank.Bank;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.beans.FeatureDescriptor;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Embeddable
public class ClientIDEmb implements Serializable {

    @Column(
            name = "bic",
            nullable = false,
            updatable = false,
            columnDefinition = "CHAR(8)"
    )
    private String bic;

    @Column(
            name = "userID",
            nullable = false,
            updatable = false
    )
    private String userID;
}
