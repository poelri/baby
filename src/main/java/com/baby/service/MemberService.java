package com.baby.service;

import com.baby.config.MemberContext;
import com.baby.entity.Member;
import com.baby.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
// final 필드를 가지고 있는 생성자를 자동으로 생성할 수 있습니다.
// 이 생성자는 final 필드에 대한 초기화를 간편하게 할 수 있도록 도와줍니다
// 상수 의존성 주입
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    //회원가입 하기 ! ! !
    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    // 회원 중복체크
    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());

        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        if ("ADMIN".equals(member.getRole().toString())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new MemberContext(member, authorities);
    }


//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        Member member = memberRepository.findByEmail(email);
//
//        if(member == null ){
//            throw new UsernameNotFoundException(email);
//        }
//        return User.builder()
//                .username(member.getEmail())
//                .password(member.getPassword())
//                .build();
//    }
}
