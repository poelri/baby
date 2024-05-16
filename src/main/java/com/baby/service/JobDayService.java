package com.baby.service;

import com.baby.constant.Day;
import com.baby.entity.JobDay;
import com.baby.entity.JobPost;
import com.baby.repository.JobDayRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class JobDayService {
    private final JobDayRepository jobDayRepository;

    public Long saveJobDay(Day day, JobPost jobPost) {
        JobDay jobDay = new JobDay(jobPost, day);
        jobDayRepository.save(jobDay);

        return jobDay.getId();
    }

    public JobDay findById(Long id) {
        return jobDayRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
