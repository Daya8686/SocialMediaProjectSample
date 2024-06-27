package com.socialmediademo.entity;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class User {

	private Integer userId;
	@NotBlank(message = "User Name can't be blank")
	@Size(min = 2, max = 255, message = "User name must be minimum 2 charaters ")
	private String username;
	@Past
	@NotNull
	private LocalDate dateOfBirth;
	
	public User() {
		super();
	}
	public User(Integer userId, String username, LocalDate dateOfBirth) {
		super();
		this.userId = userId;
		this.username = username;
		this.dateOfBirth = dateOfBirth;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
}
