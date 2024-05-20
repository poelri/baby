package com.baby.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIncruitPost is a Querydsl query type for IncruitPost
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIncruitPost extends EntityPathBase<IncruitPost> {

    private static final long serialVersionUID = -718522548L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIncruitPost incruitPost = new QIncruitPost("incruitPost");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final EnumPath<com.baby.constant.Cctv> cctv = createEnum("cctv", com.baby.constant.Cctv.class);

    public final EnumPath<com.baby.constant.Country> country = createEnum("country", com.baby.constant.Country.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final EnumPath<com.baby.constant.Day> day = createEnum("day", com.baby.constant.Day.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.baby.constant.IncruitAge> incruitAge = createEnum("incruitAge", com.baby.constant.IncruitAge.class);

    public final ListPath<IncruitDay, QIncruitDay> incruitDays = this.<IncruitDay, QIncruitDay>createList("incruitDays", IncruitDay.class, QIncruitDay.class, PathInits.DIRECT2);

    public final EnumPath<com.baby.constant.IncruitGender> incruitGender = createEnum("incruitGender", com.baby.constant.IncruitGender.class);

    public final NumberPath<Integer> incruitMoney = createNumber("incruitMoney", Integer.class);

    public final NumberPath<Integer> incruitTime = createNumber("incruitTime", Integer.class);

    public final StringPath incruitTitle = createString("incruitTitle");

    public final EnumPath<com.baby.constant.JobAge> jobAge = createEnum("jobAge", com.baby.constant.JobAge.class);

    public final EnumPath<com.baby.constant.JobGender> jobGender = createEnum("jobGender", com.baby.constant.JobGender.class);

    public final EnumPath<com.baby.constant.Location> location = createEnum("location", com.baby.constant.Location.class);

    public final QMember member;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final EnumPath<com.baby.constant.Pet> pet = createEnum("pet", com.baby.constant.Pet.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QIncruitPost(String variable) {
        this(IncruitPost.class, forVariable(variable), INITS);
    }

    public QIncruitPost(Path<? extends IncruitPost> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIncruitPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIncruitPost(PathMetadata metadata, PathInits inits) {
        this(IncruitPost.class, metadata, inits);
    }

    public QIncruitPost(Class<? extends IncruitPost> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

