package com.example.Api.transaction;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

/**
 * Transaction Entity class working as the User's one
 */
@Entity(name = "TRANSACTIONS")
@Table(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @SequenceGenerator(
            name = "trx_sequence",
            sequenceName = "trx_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "trx_sequence"
    )
    private Long trxID;

    @Column(
            name = "ibanReceiver",
            updatable = false,
            columnDefinition = "CHAR(16)"
    )
    private String ibanReceiver;

    @Column(
            name = "ibanSender",
            updatable = false,
            columnDefinition = "CHAR(16)"
    )
    private String ibanSender;

    @Column(
            name = "insIDReceiver",
            updatable = false
    )
    private Long insIDReceiver;


    @Column(
            name = "operType",
            nullable = false,
            updatable = false
    )
    private String operType;

    @Column(
            name = "currency",
            updatable = false,
            nullable = false,
            columnDefinition = "CHAR(3)"
    )
    private String currency;

    @Column(
            name = "amount",
            updatable = false,
            nullable = false
    )
    private Float amount;

    @Column(
            name = "dateTime",
            updatable = false,
            nullable = false
    )
    private LocalDateTime dateTime;

    @Column(
            name = "weekend",
            nullable = false,
            columnDefinition = "Integer(1) default '0'"
    )
    private Integer weekend;

    @Column(
            name = "status",
            nullable = false,
            columnDefinition = "Integer(1) default '0'"
    )
    private Integer status;

    @Column(
            name = "comments",
            nullable = false,
            columnDefinition = "VARCHAR(255) default ''"
    )
    private String comments;

    public Transaction(String ibanReceiver, String ibanSender, String operType, String currency,
                       Float amount, LocalDateTime dateTime, Integer weekend, Integer status, String comments) {
        this.ibanReceiver = ibanReceiver;
        this.ibanSender = ibanSender;
        this.operType = operType;
        this.currency = currency;
        this.amount = amount;
        this.dateTime = dateTime;
        this.weekend = weekend;
        this.status = status;
        this.comments = comments;
    }

    public Transaction(String ibanReceiver, String operType, String currency, Float amount, LocalDateTime dateTime, Integer weekend, Integer status, String comments) {
        this.ibanReceiver = ibanReceiver;
        this.operType = operType;
        this.currency = currency;
        this.amount = amount;
        this.dateTime = dateTime;
        this.weekend = weekend;
        this.status = status;
        this.comments = comments;
    }

    public Transaction(String ibanSender, Long insIDReceiver, String operType, String currency, Float amount, LocalDateTime dateTime, Integer status, String comments, Integer weekend) {
        this.ibanSender = ibanSender;
        this.insIDReceiver = insIDReceiver;
        this.operType = operType;
        this.currency = currency;
        this.amount = amount;
        this.dateTime = dateTime;
        this.status = status;
        this.comments = comments;
        this.weekend = weekend;
    }
}
