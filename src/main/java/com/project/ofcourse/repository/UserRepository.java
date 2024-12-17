package com.project.ofcourse.repository;

import com.project.ofcourse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username); // 아이디 존재 여부 확인
    User findByUsername(String username); // 아이디로 사용자 조회
}
