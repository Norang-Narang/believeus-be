package com.example.believeus.caregiver.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCaregiverAvailability is a Querydsl query type for CaregiverAvailability
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCaregiverAvailability extends EntityPathBase<CaregiverAvailability> {

    private static final long serialVersionUID = 786192300L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCaregiverAvailability caregiverAvailability = new QCaregiverAvailability("caregiverAvailability");

    public final ListPath<String, StringPath> availableHours = this.<String, StringPath>createList("availableHours", String.class, StringPath.class, PathInits.DIRECT2);

    public final QCaregiver caregiver;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCaregiverAvailability(String variable) {
        this(CaregiverAvailability.class, forVariable(variable), INITS);
    }

    public QCaregiverAvailability(Path<? extends CaregiverAvailability> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCaregiverAvailability(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCaregiverAvailability(PathMetadata metadata, PathInits inits) {
        this(CaregiverAvailability.class, metadata, inits);
    }

    public QCaregiverAvailability(Class<? extends CaregiverAvailability> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.caregiver = inits.isInitialized("caregiver") ? new QCaregiver(forProperty("caregiver"), inits.get("caregiver")) : null;
    }

}

