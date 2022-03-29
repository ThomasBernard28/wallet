package com.example.Api.coOwner;

import com.example.Api.client.Client;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity(name = "CO_OWNER")
@Table(name = "CO_OWNER")
public class CoOwner {

    @EmbeddedId
    private CoOwnerID coOwnerID;

    @OneToOne
    @JoinColumns(value = {
            @JoinColumn(name = "bic", referencedColumnName = "bic"),
            @JoinColumn(name = "userIDOwner", referencedColumnName = "userID"),
    }, foreignKey = @ForeignKey(name = "CO_OWNER_CLIENT_VS_INST_bic_userID_fk"))
    private Client client;
}
