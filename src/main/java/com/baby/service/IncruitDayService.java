package com.baby.service;


import com.baby.entity.IncruitDay;

import com.baby.repository.IncruitDayRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class IncruitDayService {
    private final IncruitDayRepository incruitDayRepository;

    public IncruitDay findById(Long id) {
        return incruitDayRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
