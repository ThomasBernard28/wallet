package com.example.Api.client;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

/**
 * The Client Entity class
 */
@Entity(name = "CLIENTS")
@Table(name = "CLIENTS")

public class Client {
    @EmbeddedId()
    private ClientIDEmb clientIDEmb;



}
