package com.comit.notjpa.controllers;

import java.awt.print.Pageable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.comit.notjpa.dto.EmployeeDto;
import com.comit.notjpa.services.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/employees")
	public String getEmployees(@RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
		
		List<EmployeeDto> employees = employeeService.getAllEmployees(sortField,sortDir,page,size);
		long total = employeeService.fetchCount();
		int totalPages = (int) Math.ceil((double) total / size);
		
		model.addAttribute("employees", employees);
		model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
		return "employees";
	}

	@RequestMapping(value = "/employees/{id}/edit", method = RequestMethod.GET)
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		EmployeeDto employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "form";
	}

	@RequestMapping(value = "/employees/{id}/edit", method = RequestMethod.POST)
	public String updateEmployee(@PathVariable("id") Long id, @Valid @ModelAttribute("employee") EmployeeDto employee,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("employee", employee);
			return "form";
		}
		if (employeeService.checkEmailExist(employee.getEmail(), id)) {
			bindingResult.rejectValue("email", "error.employee", "Email already exists.");
			return "form";
		}
		employeeService.updateEmployee(id, employee);
		return "redirect:/employees";
	}

	@RequestMapping(value = "/employees/create", method = RequestMethod.GET)
	public String showCreateForm(Model model) {
		model.addAttribute("employee", new EmployeeDto());
		return "formCreate";
	}

	@RequestMapping(value = "/employees/create", method = RequestMethod.POST)
	public String createEmployee(@Valid @ModelAttribute("employee") EmployeeDto employee, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("employee", employee);
			return "formCreate";
		}
		if (employeeService.checkEmailExist(employee.getEmail(), null)) {
			bindingResult.rejectValue("email", "error.employee", "Email already exists.");
			return "formCreate";
		}
		employeeService.createEmployee(employee);
		return "redirect:/employees";
	}

	@RequestMapping("/employees/{id}/delete")
	public String deleteProduct(@PathVariable("id") Long id) {
		employeeService.deleteEmployee(id);
		return "redirect:/employees";
	}
}
