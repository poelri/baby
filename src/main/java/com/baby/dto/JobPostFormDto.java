package com.baby.dto;

import com.baby.constant.*;
import com.baby.entity.JobDay;
import com.baby.entity.JobPost;
import com.baby.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class JobPostFormDto {

    private Long id;

    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String jobTitle;

    private String introduce; // 자기소개

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private String createdBy;
    private String modifiedBy;

    //Enum 타입들
    private JobGender jobGender; // 구직자 성별
    private List<JobDay> jobDays; // 희망근무요일
    private JobAge jobAge; // 구직자 나이
    private Career career; // 경력
    private JobTime jobTime; // 근무시간

    private Member member;

    private static ModelMapper modelMapper = new ModelMapper();

    // dto -> entity
    public JobPost createJobPost(){
        return modelMapper.map(this, JobPost.class);
    }

    // entity -> dto
    public static JobPostFormDto of(JobPost jobPost){
        return modelMapper.map(jobPost, JobPostFormDto.class);
    }

}
