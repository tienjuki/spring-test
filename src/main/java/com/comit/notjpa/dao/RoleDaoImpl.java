package com.comit.notjpa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.comit.notjpa.entities.QRole;
import com.comit.notjpa.entities.Role;

@Repository
public class RoleDaoImpl extends AbstractDao implements RoleDao {

	@Override
	public Role findByName(String string) {
		QRole project = QRole.role;
		return queryFactory.selectFrom(project).where(project.name.eq(string)).fetchOne();
	}

	@Override
	public Role save(Role role) {
		entityManager.persist(role);
		return role;
	}

	@Override
	public List<Role> findAll() {
		QRole project = QRole.role;
		return queryFactory.selectFrom(project).fetch();
	}

}
