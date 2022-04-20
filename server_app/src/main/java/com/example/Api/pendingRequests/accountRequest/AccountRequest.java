package com.example.Api.pendingRequests.accountRequest;

import com.example.Api.client.Client;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

/**
 * Account Creation Request Entity
 */
@Entity(name = "ACCOUNT_REQUEST")
@Table(name = "ACCOUNT_REQUEST")
public class AccountRequest {


    @Id
    @SequenceGenerator(
            name = "accRequest_sequence",
            sequenceName = "accRequest_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "accRequest_sequence",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "accRequestID",
            nullable = false,
            updatable = false
    )
    private Long accRequestID;

    @OneToOne
    @JoinColumns(value = {
            @JoinColumn(name = "bic", referencedColumnName = "bic"),
            @JoinColumn(name = "userID", referencedColumnName = "userID")
    }
    )
    private Client client;

    @Column(
            name = "type",
            nullable = false,
            updatable = false,
            columnDefinition = "CHAR(2) default 'CA'"
    )
    private String type;

    @Column(
            name = "status",
            nullable = false,
            columnDefinition = "Integer(1) default '0'"
    )
    private Integer status;

    @Column(
            name = "validator",
            nullable = false,
            columnDefinition = "Integer(1) default '0'"
    )
    private Integer validator;

    @Column(
            name = "comments",
            nullable = false,
            columnDefinition = "VARCHAR(255) default '' "
    )
    private String comments;

    public AccountRequest(Client client, String type, Integer status, Integer validator, String comments) {
        this.client = client;
        this.type = type;
        this.status = status;
        this.validator = validator;
        this.comments = comments;
    }
}
