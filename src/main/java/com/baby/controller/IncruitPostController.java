package com.baby.controller;


import com.baby.dto.IncruitPostFormDto;
import com.baby.service.IncruitPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class IncruitPostController {

    private final IncruitPostService incruitPostService;


    @GetMapping(value ="/incruitPost/new")
    public String incruitPostForm(Model model) {
        model.addAttribute("incruitPostFormDto", new IncruitPostFormDto());
        return "incruitPost/incruitWrite";
    }

    // 게시글 등록 처리
    @PostMapping(value = "/incruitPost/new")
    public String incruitPostNew(@Valid IncruitPostFormDto incruitPostFormDto, Model model,
                          BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "incruitPost/incruitWrite";

        try {
            incruitPostService.saveIncruitPost(incruitPostFormDto);
        } catch (Exception e) {
            //e.printStackTrace();
            model.addAttribute("errorMessage",
                    "게시글 등록 중 에러가 발생했습니다.");
            return "incruitPost/incruitWrite";
        }
        return "redirect:/";
    }



}
