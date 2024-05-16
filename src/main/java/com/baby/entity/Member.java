package com.baby.entity;

import com.baby.constant.Role;
import com.baby.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;


@Entity
@Table(name ="member")
@Getter
@Setter
@ToString //클래스의 모든 필드를 포함한 문자열 표현을 생성하는 toString() 메서드를 자동으로 생성
public class Member {

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 엔티티의 기본 키(primary key) 값을 자동으로 생성하기 위한 전략을 지정하는 어노테이션
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){

        String password = passwordEncoder.encode(memberFormDto.getPassword());

        Member member = new Member();

        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setPassword(password);

        //관리자가 지정해줘야 하는 항목
        member.setRole(Role.ADMIN);
        //member.setRole(Role.USER);


        return member;

    }
}
