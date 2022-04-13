package com.example.Api.pensionSaving;

import com.example.Api.client.Client;
import com.example.Api.wallet.Wallet;
import lombok.*;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity(name = "PENSION_SAVINGS")
@Table(name = "PENSION_SAVINGS")
public class PensionSaving {

    @Id
    @SequenceGenerator(
            name = "pension_sequence",
            sequenceName = "pension_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "pension_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long pensionID;

    @Column(
            name = "walletID",
            nullable = false,
            updatable = false
    )
    private Long walletID;

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

    @Column(
            name = "subDate",
            nullable = false
    )
    private LocalDate subDate;

    @Column(
            name = "renewDate",
            nullable = false
    )
    private LocalDate renewDate;

    @Column(
          name = "type",
          nullable = false,
          columnDefinition = "CHAR(5)"
    )
    private String type;

    @Column(
            name = "percentage",
            nullable = false
    )
    private Float percentage;

    @Column(
            name = "balance",
            nullable = false
    )
    private Float balance;

    @Column(
            name = "activity",
            nullable = false,
            columnDefinition = "Integer(1) default '1'"
    )
    private Integer activity;

    public PensionSaving(Long walletID, String bic, String userID, LocalDate subDate, LocalDate renewDate, String type, Float percentage, Float balance, Integer activity) {
        this.walletID = walletID;
        this.bic = bic;
        this.userID = userID;
        this.subDate = subDate;
        this.renewDate = renewDate;
        this.type = type;
        this.percentage = percentage;
        this.balance = balance;
        this.activity = activity;
    }
}
