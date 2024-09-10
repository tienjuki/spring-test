package com.comit.notjpa.dao;

import java.util.Collection;
import java.util.List;

import com.comit.notjpa.dto.EmployeeDto;
import com.comit.notjpa.entities.Employee;

public interface EmployeeDao {
	long fetchCount();
	
	List<Employee> getAllEmployees(String sortField, String sortDir, int page, int size);
	
	Employee findByPk(Long id);
	
	Employee update(Employee employee);
	
	void delete(Employee employee);
	
	Employee save(Employee employee);
	
	boolean checkEmailExist(String email, Long id);

	List<Employee> getAllEmployees();

	

}
