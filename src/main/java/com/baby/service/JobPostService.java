package com.baby.service;

import com.baby.constant.Day;
import com.baby.dto.JobPostFormDto;
import com.baby.dto.PostSearchDto;
import com.baby.entity.JobDay;
import com.baby.entity.JobPost;
import com.baby.entity.Member;
import com.baby.repository.JobDayRepository;
import com.baby.repository.JobPostRepository;
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
public class JobPostService {
    private final MemberRepository memberRepository;
    private final JobPostRepository jobPostRepository;

    // 테이블에 게시물등록
    public Long saveJobPost(JobPostFormDto jobPostFormDto) throws Exception {

        JobPost jobPost = jobPostFormDto.createJobPost();

        List<JobDay> jobDayList = new ArrayList<>();
        for (JobDay jobDay : jobPostFormDto.getJobDays()) {
            if (jobDay != null && jobDay.getDay() != null) {
                jobDay = new JobDay(jobPost, jobDay.getDay());
                jobDayList.add(jobDay);
            }
        }
        jobPost.setJobDays(jobDayList);
        JobPost savedPost = jobPostRepository.save(jobPost); // insert
        return savedPost.getId();
    }

    // 게시글 가져오기
    @Transactional(readOnly = true)
    // @Transactional(readOnly = true)
    // 수정, 생성, 삭제 체크 안하고 읽기만 해! 컴퓨터 할 일 줄여주는 애 ! 조회만 해 !
    // DTL  디테일 ㅋㅋㅋㅋㅋㅋㅋ ㅋㅋㅋㅋ ㅋ ㅋ ㅋㅋㅋㅋ
    public JobPostFormDto getJobPostDtl(Long jobId) { // postId를 매개변수로 받아와서 넘겨주세여
        JobPost jobPost = jobPostRepository.findById(jobId).orElseThrow(EntityNotFoundException::new); // 에러야
        // repository 에서 postId가 있나 없나 체크 해 !
        // 있으면 다음 코드 실행하고 없으면 에러코드 나오세요~
        // ex 통장에 돈이 있나 없나 체크하는 거랑 같아오

        // entity => dto
        // public static PostFormDto of(Post post) {  return modelMapper.map(post, PostFormDto.class);} 이거랑 같은 고야~
        return JobPostFormDto.of(jobPost);
    }

    // 게시글 수정하기
    public Long updateJobPost(JobPostFormDto jobPostFormDto, String email) throws Exception {

        JobPost jobPost  = jobPostRepository.findById(jobPostFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);

        // 기존의 jobDays 컬렉션을 수정한다
        List<JobDay> existingJobDays = jobPost.getJobDays();
        existingJobDays.clear(); // 기존의 엔티티를 모두 제거

        // 새로운 jobDays 엔티티를 추가한다
        for (JobDay jd : jobPostFormDto.getJobDays()) {
            if (jd != null && jd.getDay() != null) {
                Day day = jd.getDay();
                JobDay jobDay = new JobDay(jobPost, day);
                existingJobDays.add(jobDay); // 기존의 컬렉션에 새로운 엔티티 추가
            }
        }
        JobPost update = jobPostFormDto.createJobPost();
        update.setJobDays(existingJobDays);
        update.setMember(memberRepository.findByEmail(email));
        jobPost.updateJobPost(update);

        return jobPostRepository.save(update).getId();// 변경한 post의 id 리턴
    }

    public JobPost findById(Long id) throws Exception {
        return jobPostRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Page<JobPost> getAdminPostPage(PostSearchDto postSearchDto, Pageable pageable){
        Page<JobPost> postPage = jobPostRepository.getAdminPostPage(postSearchDto, pageable);
        return postPage;
    }

    //본인확인
    public boolean validateJobPost(Long jobId, String email) {
        //로그인한 사용자
        Member loginmember = memberRepository.findByEmail(email);

        JobPost jobPost = jobPostRepository.findById(jobId)
                .orElseThrow(EntityNotFoundException::new);

        String postMember = jobPost.getCreatedBy();

        //로그인한 사용자의 이메일과 주문한 사용자의 이메일이 같은지 비교
        if(!StringUtils.equals(loginmember.getEmail(), postMember)) {
            return false;
        }
        return true;
    }


    //게시물 삭제하기
    public void deleteJobPost(Long jobId) {
        JobPost jobPost = jobPostRepository.findById(jobId)
                .orElseThrow(EntityNotFoundException::new);

        jobPostRepository.delete(jobPost);
    }

}
