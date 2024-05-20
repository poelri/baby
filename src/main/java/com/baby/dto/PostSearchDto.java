package com.baby.dto;

import com.baby.constant.IncruitGender;
import com.baby.constant.JobGender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSearchDto {
    private String searchDateType;
    private JobGender jobGender;
    private IncruitGender incruitGender;
    private String searchBy;
    private String searchQuery = "";
}
