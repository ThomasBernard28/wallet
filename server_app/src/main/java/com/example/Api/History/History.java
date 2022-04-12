
package com.example.Api.History;

import com.example.Api.balance.Balance;
import com.example.Api.transaction.Transaction;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity(name = "TRX_HISTORY")
@Table(name = "TRX_HISTORY")
public class History {


    @Id
    @Column(
            name = "trxID",
            nullable = false,
            updatable = false
    )
    private Long trxID;

    @Column(
            name = "balanceIdViewer",
            nullable = false,
            updatable = false
    )
    private Long balanceIdViewer;
    /*
    @OneToOne
    @JoinColumn(
            name = "ibanSender",
            referencedColumnName = "iban",
            foreignKey = @ForeignKey(
                    name = "TRX_HISTORY_TRANSACTIONS_ibanSender_fk"
            )
    )

     */
    @Column(
            name = "ibanSender",
            updatable = false,
            nullable = false,
            columnDefinition = "CHAR(16)"
    )
    private String ibanSender;
    /*
    @OneToOne
    @JoinColumn(
            name = "ibanReceiver",
            referencedColumnName = "iban",
            foreignKey = @ForeignKey(
                    name = "TRX_HISTORY_TRANSACTION_ibanReceiver_fk"
            )
    )

     */
    @Column(
            name = "ibanReceiver",
            nullable = false,
            updatable = false,
            columnDefinition = "CHAR(16)"
    )
    private String ibanReceiver;

    @Column(
            name = "amount",
            updatable = false,
            nullable = false
    )
    private Float amount;

    @Column(
            name = "prevBalance",
            updatable = false,
            nullable = false
    )
    private Float prevBalance;

    @Column(
            name = "nextBalance",
            updatable = false,
            nullable = false
    )
    private Float nextBalance;

    @Column(
            name = "dateTime",
            updatable = false,
            nullable = false
    )
    private LocalDateTime dateTime;

    @Column(
            name = "comments",
            nullable = false,
            updatable = false
    )
    private String comments;

}


