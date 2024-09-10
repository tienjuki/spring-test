package com.comit.notjpa.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmployeeProject is a Querydsl query type for EmployeeProject
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmployeeProject extends EntityPathBase<EmployeeProject> {

    private static final long serialVersionUID = -1337906923L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmployeeProject employeeProject = new QEmployeeProject("employeeProject");

    public final QEmployee employee;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProject project;

    public QEmployeeProject(String variable) {
        this(EmployeeProject.class, forVariable(variable), INITS);
    }

    public QEmployeeProject(Path<? extends EmployeeProject> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmployeeProject(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmployeeProject(PathMetadata metadata, PathInits inits) {
        this(EmployeeProject.class, metadata, inits);
    }

    public QEmployeeProject(Class<? extends EmployeeProject> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employee = inits.isInitialized("employee") ? new QEmployee(forProperty("employee")) : null;
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
    }

}

