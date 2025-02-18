package com.example.believeus.workingcondition.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWorkingCondition is a Querydsl query type for WorkingCondition
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWorkingCondition extends EntityPathBase<WorkingCondition> {

    private static final long serialVersionUID = 1165406109L;

    public static final QWorkingCondition workingCondition = new QWorkingCondition("workingCondition");

    public final StringPath availableTime = createString("availableTime");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public final NumberPath<Integer> preferredSalary = createNumber("preferredSalary", Integer.class);

    public QWorkingCondition(String variable) {
        super(WorkingCondition.class, forVariable(variable));
    }

    public QWorkingCondition(Path<? extends WorkingCondition> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorkingCondition(PathMetadata metadata) {
        super(WorkingCondition.class, metadata);
    }

}

