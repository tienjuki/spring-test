package com.comit.notjpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.comit.notjpa.config.CustomUserDetails;
import com.comit.notjpa.dao.UserDao;
import com.comit.notjpa.entities.User;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);  
        System.out.println(username);
        if (user != null &&  !user.isDeleted() ) {
            return new CustomUserDetails(user);
        }else{       	
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}
