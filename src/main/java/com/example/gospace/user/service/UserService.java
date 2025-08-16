package com.example.gospace.user.service;

import com.example.gospace.user.dto.MeResponse;
import com.example.gospace.user.dto.SignupRequest;
import com.example.gospace.user.entity.Role;
import com.example.gospace.user.entity.User;
import com.example.gospace.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
                .role(Role.ROLE_USER)
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

    @Transactional
    public void verifySchool(User user, String schoolName, String studentCardUrl) {
        user.verifySchool(schoolName, studentCardUrl);

    }
}
