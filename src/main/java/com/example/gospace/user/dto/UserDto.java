
package com.example.gospace.user.dto;

import com.example.gospace.user.entity.Role;
import com.example.gospace.user.entity.User;

import java.time.LocalDateTime;

public final class UserDto {

    // 응답 DTO
    public record Resp(
            Long id,
            String email,
            String nickname,
            int graduationYear,
            Role role,
            String studentCardUrl,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        // Entity -> DTO 변환
        public static Resp fromEntity(User u) {
            return new Resp(
                    u.getId(),
                    u.getEmail(),
                    u.getNickname(),
                    u.getGraduationYear(),
                    u.getRole(),
                    u.getStudentCardUrl(),
                    u.getCreatedAt(),
                    u.getUpdatedAt()
            );
        }
    }
}
