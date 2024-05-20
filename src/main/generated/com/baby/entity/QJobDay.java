package com.baby.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJobDay is a Querydsl query type for JobDay
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJobDay extends EntityPathBase<JobDay> {

    private static final long serialVersionUID = -1454237281L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJobDay jobDay = new QJobDay("jobDay");

    public final EnumPath<com.baby.constant.Day> day = createEnum("day", com.baby.constant.Day.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QJobPost jobPost;

    public QJobDay(String variable) {
        this(JobDay.class, forVariable(variable), INITS);
    }

    public QJobDay(Path<? extends JobDay> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJobDay(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJobDay(PathMetadata metadata, PathInits inits) {
        this(JobDay.class, metadata, inits);
    }

    public QJobDay(Class<? extends JobDay> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.jobPost = inits.isInitialized("jobPost") ? new QJobPost(forProperty("jobPost"), inits.get("jobPost")) : null;
    }

}

