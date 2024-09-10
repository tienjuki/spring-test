package com.comit.notjpa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comit.notjpa.dao.EmployeeDao;
import com.comit.notjpa.dao.EmployeeProjectDao;
import com.comit.notjpa.dao.ProjectDao;
import com.comit.notjpa.dto.EmployeeDto;
import com.comit.notjpa.dto.ProjectDto;
import com.comit.notjpa.entities.Employee;
import com.comit.notjpa.entities.EmployeeProject;
import com.comit.notjpa.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
    private ProjectDao projectDao;
	
	@Autowired
    private EmployeeDao employeeDao;

	@Autowired
	private EmployeeProjectDao employeeProjectDao;

    

    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectDao.findAll();
        if (projects != null) {
        	return projects.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        }
        return null;
        
    }

    public ProjectDto getProjectById(Long id) {
        Project project = projectDao.findById(id);
        return mapToDto(project);
    }
    @Transactional
    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = mapToEntity(projectDto);
        Project savedProject = projectDao.save(project);
        return mapToDto(savedProject);
    }

    public ProjectDto updateProject(Long id, ProjectDto projectDto) {
        Project project = projectDao.findById(id);
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        Project updatedProject = projectDao.save(project);
        return mapToDto(updatedProject);
    }

    public void deleteProject(Long id) {
        Project project = projectDao.findById(id);
        projectDao.delete(project);
    }

    public ProjectDto mapToDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setDescription(project.getDescription());
        projectDto.setEmployees(project.getProjectEmployees().stream().map(pe -> {
            Employee employee = pe.getEmployee();
            EmployeeDto employeeDTO = new EmployeeDto();
            employeeDTO.setEmployee_id(employee.getEmployee_id());
            employeeDTO.setFirst_name(employee.getFirst_name());
            employeeDTO.setLast_name(employee.getLast_name());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setJob_id(employee.getJob_id());
            return employeeDTO;
        }).collect(Collectors.toSet()));
        return projectDto;
    }

    public Project mapToEntity(ProjectDto projectDto) {
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.getProjectEmployees().clear();
        if (projectDto.getEmployees() != null) {
        	for (EmployeeDto employeeDto : projectDto.getEmployees()) {
                Employee employee = employeeDao.findByPk(employeeDto.getEmployee_id());
                if (employee != null) {
    				EmployeeProject ep = new EmployeeProject();
    				ep.setProject(project);
    				ep.setEmployee(employee);
    	            project.getProjectEmployees().add(ep);
    			}
                
            }
		}
        
        return project;
    }

    

    @Transactional
    public void assignEmployeeToProject(Long projectId, Long employeeId) {
        Project project = projectDao.findById(projectId);
        Employee employee = employeeDao.findByPk(employeeId);

        EmployeeProject projectEmployee = new EmployeeProject();
        projectEmployee.setProject(project);
        projectEmployee.setEmployee(employee);
        employeeProjectDao.save(projectEmployee);
    }
}