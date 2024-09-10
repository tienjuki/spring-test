package com.comit.notjpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.comit.notjpa.dto.EmployeeDto;
import com.comit.notjpa.dto.ProjectDto;
import com.comit.notjpa.entities.Employee;
import com.comit.notjpa.entities.Project;
import com.comit.notjpa.services.EmployeeService;
import com.comit.notjpa.services.ProjectService;

@Controller
public class ProjectController {

	@Autowired
    private ProjectService projectService;

   
    @RequestMapping(value = "/projects",method = RequestMethod.GET)
    public String getAllProjects(Model model) {
    	List<ProjectDto> projects = projectService.getAllProjects();
    	List<EmployeeDto> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
    	model.addAttribute("projects", projects);
    	return "project/index";
    }
    @RequestMapping(value ="/projects/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("project", new ProjectDto());
        return "project/form";
    }

    @RequestMapping(value ="/projects/create", method = RequestMethod.POST)
    public String createProject(@ModelAttribute ProjectDto project, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "project/form";
        }
        ProjectDto savedProject = projectService.createProject(project);
        redirectAttributes.addFlashAttribute("success", "Created project: " + savedProject.getName());
        return "redirect:/projects";
    }
    
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "projects/{id}/assign-employee", method = RequestMethod.POST)
    public String assignEmployeeToProject(@PathVariable Long id, @RequestParam Long employeeId, RedirectAttributes redirectAttributes) {
       
        projectService.assignEmployeeToProject(id, employeeId);
        redirectAttributes.addFlashAttribute("success", "Employee assigned to project successfully.");
        return "redirect:/projects";
    }
}