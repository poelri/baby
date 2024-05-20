package com.baby.entity;

import com.baby.constant.Day;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="incruit_day")
@Setter
@Getter
public class IncruitDay {
    @Id
    @Column(name="incruit_day_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 설정
    private Long id; // 게시글코드

    @Enumerated(EnumType.STRING)
    private Day day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="incruit_id")
    @JsonIgnore
    private IncruitPost incruitPost;

    public IncruitDay() {
    }

    public IncruitDay(IncruitPost incruitPost, Day day) {
        this.incruitPost = incruitPost;
        this.day = day;
    }

    @Override
    public String toString() {
        return "IncruitDay{" +
                "id=" + id +
                ", day=" + day +
                "}";
    }

}
