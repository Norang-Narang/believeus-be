package com.example.believeus.senior;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSenior is a Querydsl query type for Senior
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSenior extends EntityPathBase<Senior> {

    private static final long serialVersionUID = -1007870879L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSenior senior = new QSenior("senior");

    public final StringPath address = createString("address");

    public final com.example.believeus.admin.domain.QAdmin admin;

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final DatePath<java.time.LocalDate> birthDate = createDate("birthDate", java.time.LocalDate.class);

    public final EnumPath<Senior.CareGrade> careGrade = createEnum("careGrade", Senior.CareGrade.class);

    public final StringPath careNeeds = createString("careNeeds");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final EnumPath<Senior.Gender> gender = createEnum("gender", Senior.Gender.class);

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> weight = createNumber("weight", Integer.class);

    public QSenior(String variable) {
        this(Senior.class, forVariable(variable), INITS);
    }

    public QSenior(Path<? extends Senior> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSenior(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSenior(PathMetadata metadata, PathInits inits) {
        this(Senior.class, metadata, inits);
    }

    public QSenior(Class<? extends Senior> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new com.example.believeus.admin.domain.QAdmin(forProperty("admin"), inits.get("admin")) : null;
    }

}

