package com.baby.repository;

import com.baby.constant.Day;
import com.baby.entity.JobDay;
import com.baby.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobDayRepository extends JpaRepository<JobDay, Long> {
    List<JobDay> findByJobPostIdOrderByIdAsc(Long jobId);
}
