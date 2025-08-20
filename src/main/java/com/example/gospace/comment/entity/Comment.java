package com.example.gospace.comment.entity;


import com.example.gospace.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "comments")

public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "is_anonymous", nullable = false)
    private boolean isAnonymous;

    //익명 닉네임 키 - user 엔티티와 연관관계 매핑 1대1

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public void setPost(Post post) {

        if (this.post != null && this.post.getComments() != null) {
            this.post.getComments().remove(this);
        }

        this.post = post;
        if (post != null && post.getComments() != null && !post.getComments().contains(this)) {
            post.getComments().add(this);
        }
    }
//    public void setUser(User user) {
//        this.user = user;
//        if (!user.getComments().contains(this)) {
//            user.getComments().add(this);
//        }
//    }
}
