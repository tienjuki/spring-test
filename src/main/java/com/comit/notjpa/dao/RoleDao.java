package com.comit.notjpa.dao;

import java.util.List;

import com.comit.notjpa.entities.Role;

public interface RoleDao {

	Role findByName(String string);

	Role save(Role role);

	List<Role> findAll();

}
