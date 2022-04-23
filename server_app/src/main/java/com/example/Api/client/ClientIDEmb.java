package com.example.Api.client;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

/**
 * The Client Composite Primary key class
 */
@Embeddable
public class ClientIDEmb implements Serializable {

    @Column(
            name = "bic",
            updatable = false,
            columnDefinition = "CHAR(8)",
            nullable = false
    )
    private String bic;

    @Column(
            name = "userID",
            updatable = false,
            nullable = false
    )
    private String userID;
}
