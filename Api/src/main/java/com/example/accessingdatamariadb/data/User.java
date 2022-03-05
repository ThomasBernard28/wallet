package com.example.accessingdatamariadb.data;

import javax.persistence.*;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


@Entity(name = "USERS")
@Table(
		name = "USERS",
		uniqueConstraints = {
				@UniqueConstraint(name = "USERS_natID_unique", columnNames = "natID")
		}
)
public class User {
	@Id
	@Column(
			name = "userID",
			updatable = false,
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String userID;

	@Column(
			name = "natID",
			updatable = false,
			nullable = false
	)
	private Integer natID;

	@Column(
			name = "psswd",
			nullable = false,
			unique = true
	)
	private String password;

	@Column(
			name = "lastName",
			updatable = false,
			nullable = false
	)
	private String lastName;

	@Column(
			name = "firstName",
			updatable = false,
			nullable = false
	)
	private String firstName;

	@Column(
			name = "language",
			nullable = false
	)
	private String language;
}
