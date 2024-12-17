package com.project.ofcourse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // Auditing 활성화
@Table(name = "users") // 테이블 이름 지정
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략: AUTO_INCREMENT
    private Long id; // 기본 키

    @Column(nullable = false, unique = true, length = 50) // 아이디 (중복 불가)
    private String username;

    @Column(nullable = false, length = 255) // 비밀번호 (암호화되어 저장됨)
    private String password;

    @Column(nullable = false, length = 20) // 사용자 권한 (예: ROLE_USER, ROLE_ADMIN)
    private String role;

    @CreatedDate // 생성일자 (insert 시 자동으로 설정됨)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // 수정일자 (update 시 자동으로 설정됨)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
