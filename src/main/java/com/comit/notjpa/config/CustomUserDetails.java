package com.comit.notjpa.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.comit.notjpa.entities.Role;
import com.comit.notjpa.entities.User;



public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	private User user;
     
    public CustomUserDetails(User user) {
        this.user = user;
    }
 
    public String getName() {
        return this.user.getUsername();
    }
    
    public Long getId() {
        return this.user.getId();
    }

	public void setUsername(String username) {
		this.user.setUsername(username);
	}
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 List<Role> roles = user.getRoles();
	        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	         
	        for (Role role : roles) {
	            authorities.add(new SimpleGrantedAuthority(role.getName()));
	        }
	        return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		 return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}
