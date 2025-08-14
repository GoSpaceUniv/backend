package com.example.gospace.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name =" nickname", nullable = false, unique = true, length = 10)
    private String nickname;

    @Column(name = "graduation_year", nullable = false)
    private int graduationYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 10)
    private Role role;

    //AWS S3
    @Column(name = "student_card_url", nullable = false, length = 255)
    private String studentCardUrl;

    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;


    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

    //soft delete
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    //    FK -> School(id)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "school_id", nullable = false)
//    private School school;


    //삭제(soft delete)
    public void delete(){
        this.deletedAt = LocalDateTime.now();
    }

    //닉네임 변경
    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

    //비밀번호 변경
    public void updatePassword(String password){
        this.password = password;
    }
}
