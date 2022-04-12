package com.example.Api.History;

import com.example.Api.balance.Balance;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Embeddable
public class HistoryIDEmb implements Serializable {

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
}
