package com.comit.notjpa.dto;

import java.util.Set;

public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private Set<EmployeeDto> employees;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<EmployeeDto> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<EmployeeDto> employees) {
		this.employees = employees;
	}
   
}