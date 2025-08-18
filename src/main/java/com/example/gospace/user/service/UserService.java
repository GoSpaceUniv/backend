package com.example.gospace.user.service;

import com.example.gospace.common.error.ErrorCode;
import com.example.gospace.common.exception.BusinessException;
import com.example.gospace.user.dto.MeResponse;
import com.example.gospace.user.dto.SignupRequest;
import com.example.gospace.user.dto.UserDto;
import com.example.gospace.user.entity.Role;
import com.example.gospace.user.entity.User;
import com.example.gospace.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Long signup(SignupRequest req) {
        if (userRepository.existsByEmail(req.email())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        if (userRepository.existsByNickname(req.nickname())) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        User user = User.builder()
            .email(req.email())
            .password(passwordEncoder.encode(req.password()))
            .nickname(req.nickname())
            .graduationYear(req.graduationYear())
            .role(Role.USER)
            .build();

        return userRepository.save(user).getId();
    }

    public MeResponse me(User user) {
        return new MeResponse(
            user.getId(),
            user.getEmail(),
            user.getNickname(),
            user.getGraduationYear(),
            user.getRole(),
            user.getSchoolName(),
            user.getStudentCardUrl()
        );
    }

    /**
     *
     */
    @Transactional
    public void verifySchool(User user, String schoolName, String studentCardUrl) {
        user.verifySchool(schoolName, studentCardUrl);
    }

    // 유저 정보 조회
    public UserDto.Resp getUserInfo(Long userId) {
        if (userId == null || userId <= 0) {
            throw new BusinessException(ErrorCode.INVALID_USER_ID);
        }
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return UserDto.Resp.fromEntity(user);
    }

    // 내가 쓴 글 조회
    public Page<PostSummary> getMyPosts(Long userId, Pageable pageable) {
        validatePageable(pageable);

        try {
            String countJpql = "select count(p.id) from Post p where p.user.id = :userId";
            String dataJpql = """
                    select new com.example.gospace.user.service.UserService$PostSummary(
                        p.id,
                        (case when p.school is null then null else p.school.id end),
                        p.category,
                        p.isAnonymous,
                        p.title,
                        p.createdAt,
                        p.updatedAt
                    )
                    from Post p
                    where p.user.id = :userId
                    order by p.createdAt desc
                """;

            long total = em.createQuery(countJpql, Long.class)
                .setParameter("userId", userId)
                .getSingleResult();

            TypedQuery<PostSummary> query = em.createQuery(dataJpql, PostSummary.class)
                .setParameter("userId", userId);

            List<PostSummary> content = query
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

            return new PageImpl<>(content, pageable, total);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.MYPAGE_POSTS_QUERY_FAILED);
        }
    }

    // 댓글 조회
    public Page<CommentSummary> getMyComments(Long userId, Pageable pageable) {
        validatePageable(pageable);

        try {
            String countJpql = "select count(c.id) from Comment c where c.user.id = :userId";
            String dataJpql = """
                    select new com.example.gospace.user.service.UserService$CommentSummary(
                        c.id,
                        c.post.id,
                        c.isAnonymous,
                        c.content,
                        c.createdAt,
                        c.updatedAt
                    )
                    from Comment c
                    where c.user.id = :userId
                    order by c.createdAt desc
                """;

            long total = em.createQuery(countJpql, Long.class)
                .setParameter("userId", userId)
                .getSingleResult();

            List<CommentSummary> content = em.createQuery(dataJpql, CommentSummary.class)
                .setParameter("userId", userId)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

            return new PageImpl<>(content, pageable, total);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.MYPAGE_COMMENTS_QUERY_FAILED);
        }
    }

    // 멘토 답변 조회
    public Page<AnswerSummary> getMyAnswers(Long userId, Pageable pageable) {
        validatePageable(pageable);

        try {
            String countJpql = """
                    select count(a.id)
                    from Answer a
                    where a.userId = :userId
                """;

            String dataJpql = """
                    select new com.example.gospace.user.service.UserService$AnswerSummary(
                        a.id,
                        a.questionId,
                        a.content,
                        a.createdAt,
                        a.updatedAt
                    )
                    from Answer a
                    where a.userId = :userId
                    order by a.createdAt desc
                """;

            long total = em.createQuery(countJpql, Long.class)
                .setParameter("userId", userId)
                .getSingleResult();

            List<AnswerSummary> content = em.createQuery(dataJpql, AnswerSummary.class)
                .setParameter("userId", userId)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

            return new PageImpl<>(content, pageable, total);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.MYPAGE_ANSWERS_QUERY_FAILED);
        }
    }

    private void validatePageable(Pageable pageable) {
        if (pageable == null) {
            throw new BusinessException(ErrorCode.PAGEABLE_REQUIRED);
        }
        int size = pageable.getPageSize();
        if (size <= 0 || size > 100) {
            throw new BusinessException(ErrorCode.INVALID_PAGEABLE);
        }
    }

    //DTO 변경
    public record UserInfo(
        Long id, String email, String nickname, int graduationYear,
        Role role, String studentCardUrl,
        LocalDateTime createdAt, LocalDateTime updatedAt
    ) {

    }

    public record PostSummary(
        Long id, Long schoolId, String category,
        boolean isAnonymous, String title,
        LocalDateTime createdAt, LocalDateTime updatedAt
    ) {

    }

    public record CommentSummary(
        Long id, Long postId, boolean isAnonymous,
        String content, LocalDateTime createdAt, LocalDateTime updatedAt
    ) {

    }

    public record AnswerSummary(
        Long id, Long questionId, String content,
        LocalDateTime createdAt, LocalDateTime updatedAt
    ) {

    }
}
