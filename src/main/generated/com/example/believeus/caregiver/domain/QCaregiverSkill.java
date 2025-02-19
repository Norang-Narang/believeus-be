package com.example.believeus.caregiver.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCaregiverSkill is a Querydsl query type for CaregiverSkill
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCaregiverSkill extends EntityPathBase<CaregiverSkill> {

    private static final long serialVersionUID = 2056458528L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCaregiverSkill caregiverSkill = new QCaregiverSkill("caregiverSkill");

    public final QCaregiver caregiver;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath skillName = createString("skillName");

    public QCaregiverSkill(String variable) {
        this(CaregiverSkill.class, forVariable(variable), INITS);
    }

    public QCaregiverSkill(Path<? extends CaregiverSkill> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCaregiverSkill(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCaregiverSkill(PathMetadata metadata, PathInits inits) {
        this(CaregiverSkill.class, metadata, inits);
    }

    public QCaregiverSkill(Class<? extends CaregiverSkill> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.caregiver = inits.isInitialized("caregiver") ? new QCaregiver(forProperty("caregiver"), inits.get("caregiver")) : null;
    }

}

