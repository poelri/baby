package com.baby.entity;

import com.baby.constant.*;
import com.baby.dto.JobPostFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="job_post")
@Setter
@Getter
@ToString
public class JobPost extends BaseEntity{
    @Id
    @Column(name="job_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 설정
    private Long id; // 게시글코드

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @Column(nullable = false)
    private String jobTitle; //게시글제목

    @Enumerated(EnumType.STRING)
    private JobGender jobGender; // 구직자의 성별

    @Enumerated(EnumType.STRING)
    private JobAge jobAge; // 구직자의 나이

    @OneToMany(mappedBy = "jobPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY
            , orphanRemoval = true)
    private List<JobDay> jobDays = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name="job_day_id")
//    private JobDay day; // 희망 근무 요일

    @Enumerated(EnumType.STRING) // 빈값 노놉입니다~
    private JobTime jobTime; // 근무시간

    @Enumerated(EnumType.STRING)
    private Career career; // 경력

    private String introduce; // 자기소개

    public void updateJobPost(JobPost jobPost){
        this.jobTitle = jobPost.getJobTitle();
        this.jobGender = jobPost.getJobGender();
        this.jobAge = jobPost.getJobAge();
        this.career = jobPost.getCareer();
        this.jobTime = jobPost.getJobTime();
        this.introduce = jobPost.getIntroduce();
        this.jobDays = jobPost.getJobDays();
        this.member = jobPost.getMember();
    }


}
