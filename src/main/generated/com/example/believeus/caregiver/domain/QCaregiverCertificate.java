package com.example.believeus.caregiver.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCaregiverCertificate is a Querydsl query type for CaregiverCertificate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCaregiverCertificate extends EntityPathBase<CaregiverCertificate> {

    private static final long serialVersionUID = 1774776614L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCaregiverCertificate caregiverCertificate = new QCaregiverCertificate("caregiverCertificate");

    public final QCaregiver caregiver;

    public final StringPath certificateNumber = createString("certificateNumber");

    public final EnumPath<CertificateType> certificateType = createEnum("certificateType", CertificateType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCaregiverCertificate(String variable) {
        this(CaregiverCertificate.class, forVariable(variable), INITS);
    }

    public QCaregiverCertificate(Path<? extends CaregiverCertificate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCaregiverCertificate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCaregiverCertificate(PathMetadata metadata, PathInits inits) {
        this(CaregiverCertificate.class, metadata, inits);
    }

    public QCaregiverCertificate(Class<? extends CaregiverCertificate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.caregiver = inits.isInitialized("caregiver") ? new QCaregiver(forProperty("caregiver")) : null;
    }

}

