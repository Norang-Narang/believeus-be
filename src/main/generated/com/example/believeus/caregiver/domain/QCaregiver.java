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

    public static final QCaregiver caregiver = new QCaregiver("caregiver");

    public final ListPath<CaregiverCertificate, QCaregiverCertificate> certificates = this.<CaregiverCertificate, QCaregiverCertificate>createList("certificates", CaregiverCertificate.class, QCaregiverCertificate.class, PathInits.DIRECT2);

    public final StringPath experienceYears = createString("experienceYears");

    public final BooleanPath hasDementiaTraining = createBoolean("hasDementiaTraining");

    public final BooleanPath hasVehicle = createBoolean("hasVehicle");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduction = createString("introduction");

    public final StringPath majorExperience = createString("majorExperience");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public final StringPath username = createString("username");

    public QCaregiver(String variable) {
        super(Caregiver.class, forVariable(variable));
    }

    public QCaregiver(Path<? extends Caregiver> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCaregiver(PathMetadata metadata) {
        super(Caregiver.class, metadata);
    }

}

