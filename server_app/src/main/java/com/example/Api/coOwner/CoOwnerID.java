package com.example.Api.coOwner;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Embeddable
public class CoOwnerID implements Serializable {

    @Column(
            name = "walletID_coOwner",
            nullable = false,
            updatable = false
    )
    private Long walletID;

    @Column(
            name = "ibanOwner",
            nullable = false,
            updatable = false,
            columnDefinition = "CHAR(16)"
    )
    private String ibanOwner;

    @Column(
            name = "userID_coOwner",
            nullable = false,
            updatable = false
    )
    private String userID_coOwner;
}
