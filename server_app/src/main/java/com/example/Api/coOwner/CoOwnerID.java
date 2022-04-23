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

/**
 * The CoOwner composite Primary key class
 */
@Embeddable
public class CoOwnerID implements Serializable {

    @Column(
            name = "walletID_coOwner",
            updatable = false,
            nullable = false
    )
    private Long walletID;

    @Column(
            name = "ibanOwner",
            updatable = false,
            columnDefinition = "CHAR(16)",
            nullable = false
    )
    private String ibanOwner;

    @Column(
            name = "userID_coOwner",
            updatable = false,
            nullable = false
    )
    private String userID_coOwner;
}
