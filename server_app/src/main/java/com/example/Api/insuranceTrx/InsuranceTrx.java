package com.example.Api.insuranceTrx;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity(name = "INSURANCE_TRX")
@Table(name = "INSURANCE_TRX")
public class InsuranceTrx {

    @Id
    @SequenceGenerator(
            name = "insTrx_sequence",
            sequenceName = "insTrx_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "insTrx_sequence",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "insTrxID",
            nullable = false,
            updatable = false
    )
    private Long insTrxID;

    @Column(
            name = "insID",
            nullable = false,
            updatable = false
    )
    private Long insID;

    @Column(
            name = "ibanSender",
            nullable = false,
            updatable = false
    )
    private String ibanSender;

    @Column
    private String operType;

    private String currency;

    private Float amount;

    private LocalDateTime dateTime;

    private Integer status;
}
