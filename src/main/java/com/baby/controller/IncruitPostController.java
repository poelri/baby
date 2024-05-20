package com.baby.controller;


import com.baby.dto.IncruitPostFormDto;
import com.baby.dto.IncruitPostFormDto;
import com.baby.dto.IncruitPostFormDto;
import com.baby.dto.PostSearchDto;
import com.baby.entity.IncruitPost;
import com.baby.entity.Member;
import com.baby.repository.MemberRepository;
import com.baby.service.IncruitPostService;
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

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class IncruitPostController {

    private final IncruitPostService incruitPostService;
    private final MemberRepository memberRepository;

    @GetMapping(value ="/incruitPost/new")
    public String incruitPostForm(Model model) {
        model.addAttribute("incruitPostFormDto", new IncruitPostFormDto());
        return "incruitPost/incruitWrite";
    }

    // 게시글 등록 처리
    @PostMapping(value = "/incruitPost/new")
    public @ResponseBody ResponseEntity incruitPostNew(@Valid @RequestBody IncruitPostFormDto incruitPostFormDto, Model model,
                                                       BindingResult bindingResult, Principal principal ) {
       //principal 현재 로그인된 사람의 정보

        if(bindingResult.hasErrors()) return new ResponseEntity<>("입력값이 올바르지 않습니다.", HttpStatus.BAD_REQUEST);

        try {
            Member member = memberRepository.findByEmail(principal.getName());
            incruitPostFormDto.setMember(member);
            Long incruitId = incruitPostService.saveIncruitPost(incruitPostFormDto);

            return new ResponseEntity<Long>(incruitId, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage",
                    "게시글 등록 중 에러가 발생했습니다.");
            return new ResponseEntity<>("게시글 등록 중 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    // 게시물 수정 페이지 보기
    @GetMapping(value = "/incruitPost/{incruitId}")
    public String incruitPostDtl(@PathVariable("incruitId") Long incruitId, Model model){
        try {
            IncruitPostFormDto incruitPostFormDto = incruitPostService.getIncruitPostDtl(incruitId);
            model.addAttribute("incruitPostFormDto", incruitPostFormDto);
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errorMessage",
                    "상품 정보를 가져오는 도중 에러가 발생했습니다.");

            // 에러 발생시 비어있는 객체를 넘겨준다.
            model.addAttribute("postFormDto", new IncruitPostFormDto());
            return "post/rewrite"; // 게시물 등록화면으로 다시 이동
        }

        return "incruitPost/incruitRewrite";
    }

    // 게시물 수정(update)
    @PostMapping(value = "/incruitPost/{incruitId}")
    public @ResponseBody ResponseEntity incruitPostUpdate(@Valid @RequestBody IncruitPostFormDto incruitPostFormDto, Model model, BindingResult bindingResult,
                                                      @PathVariable("incruitId") Long incruitId, Principal principal){
        if(bindingResult.hasErrors()) return new ResponseEntity<>("입력값이 올바르지 않습니다.", HttpStatus.BAD_REQUEST);

        IncruitPostFormDto getIncruitPostFormDto = incruitPostService.getIncruitPostDtl(incruitId);
        try {
            incruitPostFormDto.setId(incruitId);
            incruitPostService.updateIncruitPost(incruitPostFormDto, principal.getName());
            return new ResponseEntity<Long>(incruitId, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage",
                    "상품 수정중 에러가 발생했습니다.");
            model.addAttribute("incruitPostFormDto", getIncruitPostFormDto);
            return new ResponseEntity<>("게시글 수정 중 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }

    }

    // 게시글 리스트 보기
    @GetMapping(value = {"/incruitPost/incruitList", "/incruitPost/incruitList/{page}"})
    public String incruitPostList(Model model, PostSearchDto postSearchDto,
                              @PathVariable(value = "page") Optional<Integer> page){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,5);

        Page<IncruitPost> posts = incruitPostService.getAdminPostPage(postSearchDto, pageable);
        model.addAttribute("posts", posts);
        model.addAttribute("postSearchDto", postSearchDto);
        model.addAttribute("maxPage", 5);

        return "incruitPost/incruitList";
    }

    // 포스트 상세 페이지
    @GetMapping(value = "/incruitPost/incruitView/{incruitId}")
    public String incruitPostView(Model model, @PathVariable("incruitId") Long incruitId,
                              Principal principal){
        IncruitPostFormDto incruitPostFormDto = incruitPostService.getIncruitPostDtl(incruitId);
        model.addAttribute("incruitPost", incruitPostFormDto);
        model.addAttribute("memberId", principal.getName());
        return "incruitPost/incruitView";
    }
    //포스트 삭제
    @DeleteMapping(value = "/incruitPost/delete/{incruitId}")
    public @ResponseBody ResponseEntity deleteIncruitPost(@PathVariable(value = "incruitId") Long incruitId,
                                                      Principal principal) {
        if(!incruitPostService.validateIncruitPost(incruitId, principal.getName())) {
            return new ResponseEntity<String>("포스트 삭제 권한이 없습니다.",
                    HttpStatus.FORBIDDEN);
        }
        incruitPostService.deleteIncruitPost(incruitId);
        return new ResponseEntity<Long>(incruitId, HttpStatus.OK);
    }


}
