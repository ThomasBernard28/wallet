package com.example.Api.clientVsInstitution;


import com.example.Api.institution.Institution;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity(name = "CLIENT_VS_INST")
@Table(name = "CLIENT_VS_INST")
@IdClass(ClientId.class)
public class Client {

    @Id
    @ManyToOne
    @JoinColumn(
            name = "bic",
            nullable = false,
            referencedColumnName = "bic",
            foreignKey = @ForeignKey(
                    name = "CLIENT_VS_INST_INSTITUTIONS_bic_fk"
            )
    )
    private Institution institution;

    @Id
    @Column(
            name = "userID",
            nullable = false,
            updatable = false
    )
    private String userID;

}
