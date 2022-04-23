
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

/**
 * Insurance info entity
 */
@Entity(name = "INSURANCES_INFO")
@Table(name = "INSURANCES_INFO")
public class InsuranceInfo {

    @Id
    @Column(
            name = "insType",
            updatable = false,
            columnDefinition = "CHAR(5)",
            nullable = false
    )
    private String insType;

    @Column(
            name = "insName",
            updatable = false,
            nullable = false
    )
    private String insName;

    //duration until renew in month
    @Column(
            name = "duration",
            columnDefinition = "Integer(2)",
            nullable = false
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
