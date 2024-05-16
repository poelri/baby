package com.baby.controller;

import com.baby.dto.JobPostFormDto;
import com.baby.dto.PostSearchDto;
import com.baby.entity.JobPost;
import com.baby.entity.Member;
import com.baby.repository.JobPostRepository;
import com.baby.repository.MemberRepository;
import com.baby.service.JobPostService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostService jobPostService;
    private final MemberRepository memberRepository;
    private final JobPostRepository jobPostRepository;

    @GetMapping(value ="/jobPost/new")
    public String jobPostForm(Model model){
        model.addAttribute("jobPostFormDto", new JobPostFormDto());
        return "jobPost/jobWrite";
    }
    
    // 게시물 등록 처리
    @PostMapping(value = "/jobPost/new")
    public String jobPostNew(@Valid @RequestBody JobPostFormDto jobPostFormDto, Model model,
                             BindingResult bindingResult){

        if(bindingResult.hasErrors()) return "jobPost/jobWrite";


        try {
//            Member member = memberRepository.findByEmail(principal.getName());
//            jobPostFormDto.setMember(member);
            jobPostService.saveJobPost(jobPostFormDto);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage",
                    "게시글 등록 중 에러가 발생했습니다.");
            return "jobPost/jobWrite";
        }
        return "redirect:/";
    }

    // 게시물 수정 페이지 보기
    @GetMapping(value = "/jobPost/{jobId}")
    public String jobPostDtl(@PathVariable("jobId") Long jobId, Model model){
        try {
            JobPostFormDto jobPostFormDto = jobPostService.getJobPostDtl(jobId);
            model.addAttribute("jobPostFormDto", jobPostFormDto);
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errorMessage",
                    "상품 정보를 가져오는 도중 에러가 발생했습니다.");

            // 에러 발생시 비어있는 객체를 넘겨준다.
            model.addAttribute("postFormDto", new JobPostFormDto());
            return "post/rewrite"; // 게시물 등록화면으로 다시 이동
        }

        return "jobPost/jobRewrite";
    }

    // 게시물 수정(update)
    @PostMapping(value = "/jobPost/{jobId}")
    public String jobPostUpdate(@Valid JobPostFormDto jobPostFormDto, Model model, BindingResult bindingResult,
                             @PathVariable("jobId") Long jobId){
        if(bindingResult.hasErrors()) return "jobPost/jobRewrite"; // 유효성 체크에서 걸리면

        JobPostFormDto getJobPostFormDto = jobPostService.getJobPostDtl(jobId);
        try {
            jobPostService.updateJobPost(jobPostFormDto);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage",
                    "상품 수정중 에러가 발생했습니다.");
            model.addAttribute("jobPostFormDto", getJobPostFormDto);
            return "jobPost/jobRewrite";
        }

        return "redirect:/jobPost/jobList";
    }

    // 게시글 리스트 보기
    @GetMapping(value = {"/jobPost/jobList", "/jobPost/jobList/{page}"})
    public String jobPostList(Model model, PostSearchDto postSearchDto,
                              @PathVariable(value = "page") Optional<Integer> page){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,5);

        Page<JobPost> posts = jobPostService.getAdminPostPage(postSearchDto, pageable);
        model.addAttribute("posts", posts);
        model.addAttribute("postSearchDto", postSearchDto);
        model.addAttribute("maxPage", 5);

        return "jobPost/jobList";
    }

    // 포스트 상세 페이지
    @GetMapping(value = "/jobPost/jobView/{jobId}")
    public String jobPostView(Model model, @PathVariable("jobId") Long jobId,
                           Principal principal){
        JobPostFormDto jobPostFormDto = jobPostService.getJobPostDtl(jobId);
        model.addAttribute("jobPost", jobPostFormDto);
        model.addAttribute("memberId", principal.getName());
        return "jobPost/jobView";
    }



    //포스트 삭제
    @DeleteMapping(value = "/jobPost/delete/{jobId}")
    public @ResponseBody ResponseEntity deleteJobPost(@PathVariable(value = "jobId") Long jobId,
                                                   Principal principal) {
        if(!jobPostService.validateJobPost(jobId, principal.getName())) {
            return new ResponseEntity<String>("포스트 삭제 권한이 없습니다.",
                    HttpStatus.FORBIDDEN);
        }
        jobPostService.deleteJobPost(jobId);
        return new ResponseEntity<Long>(jobId, HttpStatus.OK);
    }

}

