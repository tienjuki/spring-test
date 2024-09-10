package com.comit.notjpa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.comit.notjpa.entities.QUser;
import com.comit.notjpa.entities.User;

@Repository
public class UserDaoImpl extends AbstractDao implements UserDao {

	@Override
	public void save(User user) {
		entityManager.persist(user);
	}

	@Override
	public List<User> findAll() {
		QUser user = QUser.user;
		return queryFactory.selectFrom(user).fetch();
	}

	@Override
	public User findById(Long id) {
		QUser user = QUser.user;
		return queryFactory.selectFrom(user).where(user.id.eq(id)).fetchOne();
	}

	@Override
	public User findByUsername(String username) {
		QUser user = QUser.user;
		return queryFactory.selectFrom(user).where(user.username.eq(username)).fetchOne();
	}

	@Override
	public void deleteById(long id) {
		User user = findById(id);
		if (user != null) {
			entityManager.remove(user);
		}
		
	}

}
