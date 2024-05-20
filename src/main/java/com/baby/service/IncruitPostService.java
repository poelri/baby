package com.baby.service;

import com.baby.constant.Day;
import com.baby.dto.IncruitPostFormDto;
import com.baby.dto.IncruitPostFormDto;
import com.baby.dto.IncruitPostFormDto;
import com.baby.dto.PostSearchDto;
import com.baby.entity.*;
import com.baby.entity.IncruitDay;
import com.baby.entity.IncruitPost;
import com.baby.repository.IncruitDayRepository;
import com.baby.repository.IncruitPostRepository;
import com.baby.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IncruitPostService {
    private final MemberRepository memberRepository;
    private final IncruitPostRepository incruitPostRepository;

    // 게시물 등록
    public Long saveIncruitPost(IncruitPostFormDto incruitPostFormDto) throws Exception {

        IncruitPost incruitPost = incruitPostFormDto.createIncruitPost();

        List<IncruitDay> incruitDayList = new ArrayList<>();
        for (IncruitDay incruitDay : incruitPostFormDto.getIncruitDays()) {
            if (incruitDay != null && incruitDay.getDay() != null) {
                incruitDay = new IncruitDay(incruitPost, incruitDay.getDay());
                incruitDayList.add(incruitDay);
            }
        }
        incruitPost.setIncruitDays(incruitDayList);
        IncruitPost savedPost = incruitPostRepository.save(incruitPost); // insert
        return savedPost.getId();
    }



    // 게시글 가져오기
    @Transactional(readOnly = true)
    // @Transactional(readOnly = true)
    // 수정, 생성, 삭제 체크 안하고 읽기만 해! 컴퓨터 할 일 줄여주는 애 ! 조회만 해 !
    // DTL  디테일 ㅋㅋㅋㅋㅋㅋㅋ ㅋㅋㅋㅋ ㅋ ㅋ ㅋㅋㅋㅋ
    public IncruitPostFormDto getIncruitPostDtl(Long incruitId) { // postId를 매개변수로 받아와서 넘겨주세여
        IncruitPost incruitPost = incruitPostRepository.findById(incruitId).orElseThrow(EntityNotFoundException::new); // 에러야
        // repository 에서 postId가 있나 없나 체크 해 !
        // 있으면 다음 코드 실행하고 없으면 에러코드 나오세요~
        // ex 통장에 돈이 있나 없나 체크하는 거랑 같아오

        // entity => dto
        // public static PostFormDto of(Post post) {  return modelMapper.map(post, PostFormDto.class);} 이거랑 같은 고야~
        return IncruitPostFormDto.of(incruitPost);
    }

    // 게시글 수정하기
    public Long updateIncruitPost(IncruitPostFormDto incruitPostFormDto, String email) throws Exception {

        IncruitPost incruitPost  = incruitPostRepository.findById(incruitPostFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);

        // 기존의 incruitDays 컬렉션을 수정한다
        List<IncruitDay> existingIncruitDays = incruitPost.getIncruitDays();
        existingIncruitDays.clear(); // 기존의 엔티티를 모두 제거

        // 새로운 incruitDays 엔티티를 추가한다
        for (IncruitDay jd : incruitPostFormDto.getIncruitDays()) {
            if (jd != null && jd.getDay() != null) {
                Day day = jd.getDay();
                IncruitDay incruitDay = new IncruitDay(incruitPost, day);
                existingIncruitDays.add(incruitDay); // 기존의 컬렉션에 새로운 엔티티 추가
            }
        }
        IncruitPost update = incruitPostFormDto.createIncruitPost();
        update.setIncruitDays(existingIncruitDays);
        update.setMember(memberRepository.findByEmail(email));
        incruitPost.updateIncruitPost(update);

        return incruitPostRepository.save(update).getId();// 변경한 post의 id 리턴
    }

    public IncruitPost findById(Long id) throws Exception {
        return incruitPostRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }



    public Page<IncruitPost> getAdminPostPage(PostSearchDto postSearchDto, Pageable pageable){
        Page<IncruitPost> postPage = incruitPostRepository.getAdminPostPage(postSearchDto, pageable);
        return postPage;
    }

    //본인확인
    public boolean validateIncruitPost(Long incruitId, String email) {
        //로그인한 사용자
        Member loginmember = memberRepository.findByEmail(email);

        IncruitPost incruitPost = incruitPostRepository.findById(incruitId)
                .orElseThrow(EntityNotFoundException::new);

        String postMember = incruitPost.getCreatedBy();

        //로그인한 사용자의 이메일과 주문한 사용자의 이메일이 같은지 비교
        if(!StringUtils.equals(loginmember.getEmail(), postMember)) {
            return false;
        }
        return true;
    }


    //게시물 삭제하기
    public void deleteIncruitPost(Long incruitId) {
        IncruitPost incruitPost = incruitPostRepository.findById(incruitId)
                .orElseThrow(EntityNotFoundException::new);

        incruitPostRepository.delete(incruitPost);
    }





}