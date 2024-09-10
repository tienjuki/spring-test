package com.comit.notjpa.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.comit.notjpa.controllers.AuthController;
import com.comit.notjpa.dto.validator.PasswordValidator;
import com.comit.notjpa.entities.Role;

public class UserDto {
	private Long id;
	@NotEmpty(message = "{Username_field_is_required}")
	private String username;
	@NotEmpty(groups = AuthController.class, message = "{Password_should_not_be_empty}")
	@PasswordValidator
	private String password;
	@Pattern(regexp = "|^\\d{10}$",  message = "{Invalid_phone_number_format}")
	private String phoneNumber;
	private String fullName;
	private String email;
	private List<Role> roles = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
