package com.baby.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJobPost is a Querydsl query type for JobPost
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJobPost extends EntityPathBase<JobPost> {

    private static final long serialVersionUID = -2131311875L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJobPost jobPost = new QJobPost("jobPost");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final EnumPath<com.baby.constant.Career> career = createEnum("career", com.baby.constant.Career.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduce = createString("introduce");

    public final EnumPath<com.baby.constant.JobAge> jobAge = createEnum("jobAge", com.baby.constant.JobAge.class);

    public final ListPath<JobDay, QJobDay> jobDays = this.<JobDay, QJobDay>createList("jobDays", JobDay.class, QJobDay.class, PathInits.DIRECT2);

    public final EnumPath<com.baby.constant.JobGender> jobGender = createEnum("jobGender", com.baby.constant.JobGender.class);

    public final EnumPath<com.baby.constant.JobTime> jobTime = createEnum("jobTime", com.baby.constant.JobTime.class);

    public final StringPath jobTitle = createString("jobTitle");

    public final QMember member;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QJobPost(String variable) {
        this(JobPost.class, forVariable(variable), INITS);
    }

    public QJobPost(Path<? extends JobPost> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJobPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJobPost(PathMetadata metadata, PathInits inits) {
        this(JobPost.class, metadata, inits);
    }

    public QJobPost(Class<? extends JobPost> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

