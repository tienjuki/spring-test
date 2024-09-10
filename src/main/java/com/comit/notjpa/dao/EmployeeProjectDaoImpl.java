package com.comit.notjpa.dao;

import org.springframework.stereotype.Repository;

import com.comit.notjpa.entities.EmployeeProject;

@Repository
public class EmployeeProjectDaoImpl extends AbstractDao implements EmployeeProjectDao {

	@Override
	public EmployeeProject save(EmployeeProject ep) {
		entityManager.persist(ep);
		return ep;
	}

}
