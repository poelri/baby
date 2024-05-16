package com.baby.entity;



import com.baby.constant.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="incruit_post") //DB에 넣을 테이블 이름!!
@Getter
@Setter
@ToString
public class IncruitPost extends BaseEntity{
    @Id
    @Column(name="incruit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 설정
    private Long id; // incruit 게시글 코드

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @Column(nullable = false)
    private String incruitTitle; //게시글 제목

    @Column(nullable = false) // 빈값 노놉입니다~
    private int incruitTime; // 근무시간

    // private LocalDateTime regDate; baseEntity에서 자동으로 가져와서 안만들랭!!
    // private LocalDateTime updateDate; baseEntity에서 자동으로 가져와서 안만들랭!!

    @Enumerated(EnumType.STRING)
    private Tip tip; //팁이다 ㅋ

    @Column(nullable = false)
    @NotEmpty
    private Integer incruitMoney;

    @Enumerated(EnumType.STRING)
    private Cctv cctv; // 씨씨티비

    @Enumerated(EnumType.STRING)
    private IncruitGender incruitGender; // 아이의 성별

    @Enumerated(EnumType.STRING) // 이거 enum 타입이고 스트링으로 할구임ㅋ
    private Pet pet; // 반려동물

    @Enumerated(EnumType.STRING)
    private IncruitAge incruitAge; // 아이 나이

    @Enumerated(EnumType.STRING)
    private Location location; // 근무 지역

    @Enumerated(EnumType.STRING)
    private Day day; // 근무 요일

    @Enumerated(EnumType.STRING)
    private Country country; // 국적


    // IncruitPost 엔티티 수정
    // public void updateIncruitPost(){

    //}










}
