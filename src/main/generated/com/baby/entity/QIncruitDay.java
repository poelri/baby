package com.baby.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIncruitDay is a Querydsl query type for IncruitDay
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIncruitDay extends EntityPathBase<IncruitDay> {

    private static final long serialVersionUID = -1685758096L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIncruitDay incruitDay = new QIncruitDay("incruitDay");

    public final EnumPath<com.baby.constant.Day> day = createEnum("day", com.baby.constant.Day.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QIncruitPost incruitPost;

    public QIncruitDay(String variable) {
        this(IncruitDay.class, forVariable(variable), INITS);
    }

    public QIncruitDay(Path<? extends IncruitDay> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIncruitDay(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIncruitDay(PathMetadata metadata, PathInits inits) {
        this(IncruitDay.class, metadata, inits);
    }

    public QIncruitDay(Class<? extends IncruitDay> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.incruitPost = inits.isInitialized("incruitPost") ? new QIncruitPost(forProperty("incruitPost"), inits.get("incruitPost")) : null;
    }

}

