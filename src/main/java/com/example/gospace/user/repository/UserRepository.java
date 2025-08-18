package com.example.gospace.user.repository;

import com.example.gospace.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // DB에 해당 email과 nickname을 가진 User가 존재할시 true/false 반환
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    Optional<User> findByEmail(String email);

}