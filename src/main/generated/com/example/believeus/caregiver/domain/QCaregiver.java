package com.example.believeus.caregiver.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCaregiver is a Querydsl query type for Caregiver
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCaregiver extends EntityPathBase<Caregiver> {

    private static final long serialVersionUID = -52957263L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCaregiver caregiver = new QCaregiver("caregiver");

    public final ListPath<CaregiverCertificate, QCaregiverCertificate> certificates = this.<CaregiverCertificate, QCaregiverCertificate>createList("certificates", CaregiverCertificate.class, QCaregiverCertificate.class, PathInits.DIRECT2);

    public final StringPath experienceYears = createString("experienceYears");

    public final BooleanPath hasDementiaTraining = createBoolean("hasDementiaTraining");

    public final BooleanPath hasVehicle = createBoolean("hasVehicle");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduction = createString("introduction");

    public final StringPath majorExperience = createString("majorExperience");

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public final com.example.believeus.auth.domain.QUser user;

    public QCaregiver(String variable) {
        this(Caregiver.class, forVariable(variable), INITS);
    }

    public QCaregiver(Path<? extends Caregiver> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCaregiver(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCaregiver(PathMetadata metadata, PathInits inits) {
        this(Caregiver.class, metadata, inits);
    }

    public QCaregiver(Class<? extends Caregiver> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.believeus.auth.domain.QUser(forProperty("user")) : null;
    }

}

