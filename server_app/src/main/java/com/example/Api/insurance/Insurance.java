package com.example.Api.insurance;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

/**
 * Insurance Entity Class
 */
@Entity(name = "INSURANCES")
@Table(name = "INSURANCES")
public class Insurance {

    @Id
    @Column(
            name = "insID",
            updatable = false,
            nullable = false
    )
    private Long insID;

    @Column(
            name = "walletID",
            updatable = false,
            nullable = false
    )
    private Long walletID;

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

    @Column(
            name = "type",
            columnDefinition = "CHAR(5)",
            nullable = false
    )
    private String type;

    @Column(
            name = "subDate",
            nullable = false
    )
    private LocalDate subDate;

    @Column(
            name = "endDate",
            nullable = false
    )
    private LocalDate endDate;


    @Column(
            name = "activity",
            columnDefinition = "Integer(1) default '0'",
            nullable = false
    )
    private Integer activity;
    
}
