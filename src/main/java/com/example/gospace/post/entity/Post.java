package com.example.gospace.post.entity;

import com.example.gospace.post.dto.UpdatePostRequestDto;
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
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "posts")

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(name = "category",nullable = false)
    private Category category;

    //익명 여부 User 엔티티 구성시 후보키 가능할듯 + Boolean(x) -> boolean(o) 사용
    @Column(name = "is_anon", nullable = false)
    private boolean isAnon;


    @Column(name = "title", nullable = false, length = 100)
    private String title;

    //길이 제한 없앨 경우 @Lob 사용 및 columnDefinition = "TEXT" 형태
    @Column(name = "content", nullable = false, length = 500)
    private String content;

//    User 엔티티로 옮겨야 하는 코드
//    @Column(name = "student_card_url", nullable = false, length = 255)
//    private String studentCardUrl;


    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;


    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

//    FK -> User(id)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

//    FK -> School(id)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "school_id", nullable = false)
//    private School school;

//    익명 닉네임 -> 대체 키 가능?
//    @Column(name = "anno_key", length = 50, unique = true)
//    private String annoKey;

    public Post(String title, String content, Category category, boolean isAnon) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.isAnon = isAnon;
    }

    public void update(UpdatePostRequestDto dto) {
        this.title = dto.title();
        this.content = dto.content();
        this.category = dto.category();
        this.isAnon = dto.isAnon();
    }
}
