package com.baby.repository;

import com.baby.entity.IncruitPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface IncruitPostRepository extends JpaRepository<IncruitPost, Long> {


    }

