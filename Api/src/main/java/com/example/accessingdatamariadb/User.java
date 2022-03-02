package com.example.accessingdatamysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombock.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private String userID;
	private Integer natID;
	private String password;
	private String lastName;
	private String firstame;
	private String language;


	public String getUserID(){return userID;}


}
