package com.baby.config;

import com.baby.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class MemberContext extends User {
    // authentication 객체에 저장 하고 싶은 값을 필드로 지정
    private final String name;

    public MemberContext(Member member, List<GrantedAuthority> autorities){
        super(member.getEmail(), member.getPassword(), autorities); //User생성자 실행
        this.name = member.getName();
    }
}
