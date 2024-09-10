package com.comit.notjpa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comit.notjpa.dao.EmployeeDao;
import com.comit.notjpa.dao.ProjectDao;
import com.comit.notjpa.dto.EmployeeDto;
import com.comit.notjpa.dto.ProjectDto;
import com.comit.notjpa.entities.Employee;
import com.comit.notjpa.entities.EmployeeProject;
import com.comit.notjpa.entities.Project;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private ProjectDao projectDao;
	
	@Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployees(String sortField, String sortDir, int page, int size) {
		
        return employeeDao.getAllEmployees(sortField,sortDir,page,size).stream()
        		.map(this::mapToDto)
        		.collect(Collectors.toList());
    }
	@Transactional(readOnly = true)
	public List<EmployeeDto> getAllEmployees() {
		 return employeeDao.getAllEmployees().stream()
	        		.map(this::mapToDto)
	        		.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public EmployeeDto getEmployeeById(Long id) {
		return mapToDto(employeeDao.findByPk(id));
	}
	@Transactional
	public Employee updateEmployee(Long id, EmployeeDto employee) {
		Employee curentEmployee = employeeDao.findByPk(id);
		if (curentEmployee != null) {
	        
			curentEmployee.setEmail(employee.getEmail());
			curentEmployee.setFirst_name(employee.getFirst_name());
			curentEmployee.setLast_name(employee.getLast_name());
			curentEmployee.setJob_id(employee.getJob_id());
			return employeeDao.update(curentEmployee);
		}
		
		return null;		
	}
	@Transactional
	public Employee createEmployee(EmployeeDto employee) {
		return employeeDao.save(mapToEntity(employee));			
	}
	@Transactional
	public void deleteEmployee(Long id) {
		Employee curentEmployee = employeeDao.findByPk(id);
		employeeDao.delete(curentEmployee);		
		
	}
	
	public boolean checkEmailExist(String email, Long id) {
		
		return employeeDao.checkEmailExist(email, id);
	}
	
	public EmployeeDto mapToDto(Employee employee) {
		
		EmployeeDto employeeDTO = new EmployeeDto();
        employeeDTO.setEmployee_id(employee.getEmployee_id());
        employeeDTO.setFirst_name(employee.getFirst_name());
        employeeDTO.setLast_name(employee.getLast_name());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setJob_id(employee.getJob_id());
        employeeDTO.setProjects(employee.getProjectEmployees().stream().map(pe->{
        	Project project = pe.getProject();
        	ProjectDto projectDto = new ProjectDto();
            projectDto.setId(project.getId());
            projectDto.setName(project.getName());
            projectDto.setDescription(project.getDescription());
            return projectDto;
        }).collect(Collectors.toSet()));
		
        return employeeDTO;
    }

    public Employee mapToEntity(EmployeeDto employeeDTO) {
    	Employee employee = new Employee();
    	employee.setEmployee_id(employeeDTO.getEmployee_id());
    	employee.setFirst_name(employeeDTO.getFirst_name());
    	employee.setLast_name(employeeDTO.getLast_name());
    	employee.setEmail(employeeDTO.getEmail());
    	employee.setJob_id(employeeDTO.getJob_id());
    	employee.getProjectEmployees().clear();

    	employee.getProjectEmployees().clear();
    	if (employeeDTO.getProjects() != null) {
    		for (ProjectDto projectDto : employeeDTO.getProjects()) {
                Project project = projectDao.findById(projectDto.getId());
                if (project != null) {
    				EmployeeProject ep = new EmployeeProject();
    				ep.setProject(project);
    				ep.setEmployee(employee);
    				employee.getProjectEmployees().add(ep);
    			}
                
            }
		}
        
        return employee;
    }

	public long fetchCount() {
		// TODO Auto-generated method stub
		return employeeDao.fetchCount();
	}

	
}
