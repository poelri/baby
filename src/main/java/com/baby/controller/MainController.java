package com.baby.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
// 모든 final 필드를 초기화하는 생성자를 생성하는 어노테이션
// 모든 필드를 초기화해야 하는 생성자로 인해 코드가 장황해질 수 있는 상황에서 특히 유용
public class MainController {
    @GetMapping(value ="/") //localhost
    public String main() {
        return "main"; //메인화면(main.html) 띄움
    }
}
