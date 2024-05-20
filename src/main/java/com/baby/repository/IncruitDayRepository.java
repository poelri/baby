package com.baby.repository;

import com.baby.entity.IncruitDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncruitDayRepository extends JpaRepository<IncruitDay, Long> {
    List<IncruitDay> findByIncruitPostIdOrderByIdAsc(Long incruitId);
}
