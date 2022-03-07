package com.example.Api.language;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity(name = "LANGUAGES")
@Table(name = "LANGUAGES")
public class Language {

    @Id
    @Column(
            name = "language",
            nullable = false,
            updatable = false,
            columnDefinition = "char"
    )
    private String language;
}
