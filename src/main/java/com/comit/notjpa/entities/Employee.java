package com.comit.notjpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="employees")
public class Employee {
	@Id
    @GeneratedValue
    private Long employee_id;
	
	@Column(nullable = false)
    private String first_name;
	
	@Column(nullable = false)
    private String last_name;
	
	@Column(nullable = false)
    private String email;
	
	@Column(nullable = false)
    private String job_id;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeeProject> projectEmployees = new HashSet<>();
    
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
	public Set<EmployeeProject> getProjectEmployees() {
		return projectEmployees;
	}
	public void setProjectEmployees(Set<EmployeeProject> projectEmployees) {
		this.projectEmployees = projectEmployees;
	}
	
	
}
