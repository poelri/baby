package com.baby.service;

import com.baby.dto.IncruitPostFormDto;
import com.baby.entity.IncruitPost;
import com.baby.repository.IncruitPostRepository;
import com.baby.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class IncruitPostService {
    //private final IncruitPostService incruitPostService;
    private final MemberRepository memberRepository;
    private final IncruitPostRepository incruitPostRepository;

    // 게시물 등록
    public Long saveIncruitPost(IncruitPostFormDto incruitPostFormDto) throws Exception{

        IncruitPost incruitPost = incruitPostFormDto.createIncruitPost();
        incruitPostRepository.save(incruitPost);// insert

        return incruitPostFormDto.getId();

    }

}
