package com.example.Api.insurance;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

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
            name = "type",
            nullable = false,
            columnDefinition = "CHAR(5)"
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
            nullable = false,
            columnDefinition = "Integer(1) default '0'"
    )
    private Integer activity;
    
}
