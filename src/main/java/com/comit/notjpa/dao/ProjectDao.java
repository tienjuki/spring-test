package com.comit.notjpa.dao;

import java.util.List;

import com.comit.notjpa.entities.Project;

public interface ProjectDao {
	Project save(Project project);

	Project findById(Long id);

	List<Project> findAll();

	void delete(Project project);

	Project update(Project project);
}
