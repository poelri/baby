package com.baby.entity;

import com.baby.constant.Day;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="job_day")
@Setter
@Getter
public class JobDay {
    @Id
    @Column(name="job_day_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 설정
    private Long id; // 게시글코드

    @Enumerated(EnumType.STRING)
    private Day day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="job_id")
    @JsonIgnore
    private JobPost jobPost;

    public JobDay() {
    }

    public JobDay(JobPost jobPost, Day day) {
        this.jobPost = jobPost;
        this.day = day;
    }

    @Override
    public String toString() {
        return "JobDay{" +
                "id=" + id +
                ", day=" + day +
                "}";
    }

}
