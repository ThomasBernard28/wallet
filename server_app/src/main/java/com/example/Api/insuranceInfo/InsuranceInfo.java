
package com.example.Api.insuranceInfo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity(name = "INSURANCES_INFO")
@Table(name = "INSURANCES_INFO")
public class InsuranceInfo {

    @Id
    @Column(
            name = "insType",
            nullable = false,
            updatable = false,
            columnDefinition = "CHAR(5)"
    )
    private String insType;

    @Column(
            name = "insName",
            nullable = false,
            updatable = false
    )
    private String insName;

    //duration until renew in month
    @Column(
            name = "duration",
            nullable = false,
            columnDefinition = "Integer(2)"
    )
    private Integer duration;

    @Column(
            name = "percentage"
    )
    private Float percentage;

    @Column(
            name = "price"
    )
    private Float price;
}
