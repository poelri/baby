package com.baby.repository;

import com.baby.constant.IncruitGender;
import com.baby.dto.PostSearchDto;
import com.baby.entity.IncruitPost;
import com.baby.entity.QIncruitPost;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

public class IncruitPostRepositoryCustomImpl implements IncruitPostRepositoryCustom {
    private JPAQueryFactory queryFactory;

    public IncruitPostRepositoryCustomImpl(EntityManager em) { // 생성자 방식으로 의존성 주입
        this.queryFactory = new JPAQueryFactory(em);
    }

    //현재 날짜로부터 이전 날짜를 구해주는 메소드
    private BooleanExpression regDtsAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now(); // 현재 날짜, 시간

        if(StringUtils.equals("all", searchDateType) || searchDateType == null) {
            return null;
        } else if (StringUtils.equals("1d", searchDateType)) {
            dateTime = dateTime.minusDays(1);
        } else if (StringUtils.equals("1w", searchDateType)) {
            dateTime = dateTime.minusWeeks(1);
        } else if (StringUtils.equals("1m", searchDateType)) {
            dateTime = dateTime.minusMonths(1);
        } else if (StringUtils.equals("6m", searchDateType)) {
            dateTime = dateTime.minusMonths(6);
        }

        return QIncruitPost.incruitPost.regDate.after(dateTime); // 몇일전 이후부터
    }
    //상태를 전체로 했을때 null이 들어있으므로 처리를 한번 해준다
    private BooleanExpression incruitGenderEq(IncruitGender incruitGender) {
        return incruitGender== null ? null : QIncruitPost.incruitPost.incruitGender.eq(incruitGender);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {
        if(StringUtils.equals("title", searchBy)) { // 타이틀 검색
            return QIncruitPost.incruitPost.incruitTitle.like("%" + searchQuery + "%");
        } else if (StringUtils.equals("createdBy", searchQuery)) { // 등록자 검색시
            return QIncruitPost.incruitPost.createdBy.like("%" + searchQuery + "%");
        }
        return null;
    }




    @Override
    public Page<IncruitPost> getAdminPostPage(PostSearchDto postSearchDto, Pageable pageable) {
        List<IncruitPost> content = queryFactory
                .selectFrom(QIncruitPost.incruitPost)
                .where(regDtsAfter(postSearchDto.getSearchDateType()),
                        incruitGenderEq(postSearchDto.getIncruitGender()),
                        searchByLike(postSearchDto.getSearchBy(), postSearchDto.getSearchQuery()))
                .orderBy(QIncruitPost.incruitPost.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.select(Wildcard.count).from(QIncruitPost.incruitPost)
                .where(regDtsAfter(postSearchDto.getSearchDateType()),
                        incruitGenderEq(postSearchDto.getIncruitGender()),
                        searchByLike(postSearchDto.getSearchBy(), postSearchDto.getSearchQuery()))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }



    private  BooleanExpression titleLike(String searchQuery){
        return StringUtils.isEmpty(searchQuery)  ? null : QIncruitPost.incruitPost.incruitTitle.like("%"+searchQuery+"%");
    }


}
