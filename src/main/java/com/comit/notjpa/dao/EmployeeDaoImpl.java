package com.comit.notjpa.dao;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.comit.notjpa.entities.Employee;
import com.comit.notjpa.entities.QEmployee;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;

@Repository
public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao {

	/*
	 * @Override public Employee findByPk(Long id) { Session session =
	 * sessionFactory.getCurrentSession(); return (Employee)
	 * session.get(Employee.class, id); }
	 */

	@Override
	public Employee findByPk(Long id) {
		QEmployee employee = QEmployee.employee;
		return queryFactory.selectFrom(employee).where(employee.employee_id.eq(id)).fetchOne();
	}

	@Override
	public Employee update(Employee employee) {
		entityManager.merge(employee);
		return employee;
	}

	@Override
	public void delete(Employee employee) {
		entityManager.remove(employee);
	}

	@Override
	public Employee save(Employee employee) {
		entityManager.persist(employee);
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees(String sortField, String sortDir, int page, int size) {
		QEmployee employee = QEmployee.employee;
		JPAQuery<Employee> query = queryFactory.selectFrom(employee);
		if (sortField != null) {
			OrderSpecifier<?> orderSpecifier = null;
			switch (sortField) {
			case "first_name":
				orderSpecifier = "desc".equalsIgnoreCase(sortDir) ? employee.first_name.desc() : employee.first_name.asc();
				break;
			case "last_name":
				orderSpecifier = "desc".equalsIgnoreCase(sortDir) ? employee.last_name.desc() : employee.last_name.asc();
				break;
			case "email":
				orderSpecifier = "desc".equalsIgnoreCase(sortDir) ? employee.email.desc() : employee.email.asc();
				break;
			case "job_id":
				orderSpecifier = "desc".equalsIgnoreCase(sortDir) ? employee.job_id.desc() : employee.job_id.asc();
				break;
			default:
				orderSpecifier = "desc".equalsIgnoreCase(sortDir) ? employee.employee_id.desc() : employee.employee_id.asc();
				break;
			}
			query = query.orderBy(orderSpecifier);
		}
		query = query.limit(size).offset(page * size);
	
        
		return query.fetch();
	}

	@Override
	public boolean checkEmailExist(String email, Long id) {
		
		QEmployee employee = QEmployee.employee;
		BooleanExpression query = employee.email.eq(email);
		if (id != null) {
			query = query.and(employee.employee_id.ne(id));
		}
		Long count = queryFactory.selectFrom(QEmployee.employee).where(query).fetchCount();
		String sql = query.toString();
		System.out.println(sql);
		return count > 0;
	}

	@Override
	public long fetchCount() {
		QEmployee employee = QEmployee.employee;
		long total = queryFactory
                .selectFrom(employee)
                .fetchCount();
		return total;
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		QEmployee employee = QEmployee.employee;
		return queryFactory.selectFrom(employee).fetch();
	}

}
