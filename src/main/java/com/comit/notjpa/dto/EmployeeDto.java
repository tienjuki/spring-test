package com.comit.notjpa.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class EmployeeDto {
	
	private Long employee_id;
	@NotNull
    private String first_name;
	@NotNull
    private String last_name;
	@Email
	@NotBlank
    private String email;
	@NotNull
    private String job_id;
	
	private Set<ProjectDto> projects;
	
	public Long getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public Set<ProjectDto> getProjects() {
		return projects;
	}
	public void setProjects(Set<ProjectDto> projects) {
		this.projects = projects;
	}
}
