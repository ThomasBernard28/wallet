package com.example.accessingdatamariadb.data;

import javax.persistence.*;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


@Entity(name = "User")
public class User {
	@Id
	private String userID;
	private Integer natID;
	private String password;
	private String lastName;
	private String firstName;
	private String language;
	private Long id;
}
