package com.comit.notjpa.dao;

import java.util.List;

import com.comit.notjpa.entities.User;

public interface UserDao {

	void save(User user1);

	List<User> findAll();

	User findById(Long id);

	User findByUsername(String username);

	void deleteById(long id);

}
