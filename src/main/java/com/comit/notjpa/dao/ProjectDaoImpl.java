package com.comit.notjpa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.comit.notjpa.entities.Project;
import com.comit.notjpa.entities.QProject;

@Repository
public class ProjectDaoImpl extends AbstractDao implements ProjectDao {

	@Override
	public Project save(Project project) {
		entityManager.persist(project);
		return project;
	}

	@Override
	public Project findById(Long id) {
		QProject project = QProject.project;
		return queryFactory.selectFrom(project).where(project.id.eq(id)).fetchOne();
	}

	@Override
	public List<Project> findAll() {
		QProject project = QProject.project;
		return queryFactory.selectFrom(project).fetch();
	}

	@Override
	public void delete(Project project) {

	}
	
	@Override
	public Project update(Project project) {
		entityManager.merge(project);
		return project;
	}
}