package com.example.believeus.admin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAdmin is a Querydsl query type for Admin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdmin extends EntityPathBase<Admin> {

    private static final long serialVersionUID = 845346065L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAdmin admin = new QAdmin("admin");

    public final StringPath address = createString("address");

    public final StringPath centerGrade = createString("centerGrade");

    public final StringPath centerName = createString("centerName");

    public final BooleanPath hasBathVehicle = createBoolean("hasBathVehicle");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduction = createString("introduction");

    public final StringPath name = createString("name");

    public final StringPath operatingPeriod = createString("operatingPeriod");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public final com.example.believeus.auth.domain.QUser user;

    public QAdmin(String variable) {
        this(Admin.class, forVariable(variable), INITS);
    }

    public QAdmin(Path<? extends Admin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAdmin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAdmin(PathMetadata metadata, PathInits inits) {
        this(Admin.class, metadata, inits);
    }

    public QAdmin(Class<? extends Admin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.believeus.auth.domain.QUser(forProperty("user")) : null;
    }

}

