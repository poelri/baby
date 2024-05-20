package com.baby.dto;

import com.baby.constant.*;
import com.baby.entity.IncruitDay;
import com.baby.entity.IncruitPost;
import com.baby.entity.JobDay;
import com.baby.entity.Member;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class IncruitPostFormDto {

    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @NotEmpty(message = "제목은 필수 입력값입니다.")
    private String IncruitTitle;

    private int incruitTime; // 근무시간

//    @NotEmpty(message = "시급은 필수 입력값입니다")
    private int incruitMoney;

    // Enum타입들 ! !
    private Cctv cctv;
    private IncruitGender incruitGender; // 아이성별
    private Pet pet; // 반려동물
    private IncruitAge incruitAge; // 아이나이
    private Location location; // 지역
    private Day day; // 근무일
    private Country country; // 국적
    private List<IncruitDay> incruitDays; // 희망근무요일
    private JobGender jobGender; // 구직자 성별
    private JobAge jobAge; // 구직자 나이

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private String createdBy;
    private String modifiedBy;

    private static ModelMapper modelMapper = new ModelMapper();

    // dto -> entity
    public IncruitPost  createIncruitPost(){
        return modelMapper.map(this,IncruitPost.class);
    }

    // entity -> dto
    public static IncruitPostFormDto of(IncruitPost incruitPost){
        return modelMapper.map(incruitPost, IncruitPostFormDto.class);
    }
}
