package com.comit.notjpa.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class RegisterDto {
	@NotEmpty
    private String userName;
	
	@NotEmpty
    private String email;
	
	@NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
