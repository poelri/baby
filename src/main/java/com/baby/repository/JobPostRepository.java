package com.baby.repository;


import com.baby.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface JobPostRepository extends JpaRepository<JobPost, Long> ,
        QuerydslPredicateExecutor<JobPost>, JobPostRepositoryCustom {}

