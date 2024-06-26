package com.baby.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class MemberFormDto {
    // 유효성 체크를 위한 검증
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;


    @NotBlank(message = "메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력하세요~")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 8, max = 16, message = "비밀번호는 8자~16자 사이로 입력해주세요.")
    private String password;


}
