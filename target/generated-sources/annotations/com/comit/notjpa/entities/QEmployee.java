package com.comit.notjpa.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmployee extends EntityPathBase<Employee> {

    private static final long serialVersionUID = -1199169180L;

    public static final QEmployee employee = new QEmployee("employee");

    public final StringPath email = createString("email");

    public final NumberPath<Long> employee_id = createNumber("employee_id", Long.class);

    public final StringPath first_name = createString("first_name");

    public final StringPath job_id = createString("job_id");

    public final StringPath last_name = createString("last_name");

    public final SetPath<EmployeeProject, QEmployeeProject> projectEmployees = this.<EmployeeProject, QEmployeeProject>createSet("projectEmployees", EmployeeProject.class, QEmployeeProject.class, PathInits.DIRECT2);

    public QEmployee(String variable) {
        super(Employee.class, forVariable(variable));
    }

    public QEmployee(Path<? extends Employee> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmployee(PathMetadata metadata) {
        super(Employee.class, metadata);
    }

}

