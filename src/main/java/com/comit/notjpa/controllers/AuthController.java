package com.comit.notjpa.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.comit.notjpa.dto.RegisterDto;
import com.comit.notjpa.services.UserService;


@Controller
public class AuthController {
	@Autowired
	private UserService userService;
    // handler method to handle home page request
    @RequestMapping("/index")
    public String home(){
        return "index";
    }
    
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterView(Model model){
    	RegisterDto registerDto = new RegisterDto();
    	model.addAttribute("registerDto",registerDto);
        return "register";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("registerDto") RegisterDto registerDto, BindingResult result, Model model){
    	
		if (result.hasErrors()) {
			model.addAttribute("registerDto",registerDto);
			return "/user/create";
		}
		
    	userService.register(registerDto);
        return "login";
    }
}