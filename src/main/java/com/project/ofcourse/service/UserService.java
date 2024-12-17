package com.project.ofcourse.service;

import com.project.ofcourse.entity.User;
import com.project.ofcourse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //아이디 중복 확인
    public boolean isUsernameDuplicate(String username) {
        return userRepository.existsByUsername(username);
    }

    //회원가입
    public void register(String username, String rawPassword) {
        if (isUsernameDuplicate(username)) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword)); //비밀번호 암호화
        user.setRole("ROLE_USER"); //기본 권한 설정
        userRepository.save(user);
    }

    // 회원 비밀번호 수정
    public void updatePassword(String username, String currentPassword, String newPassword) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }

        // 현재 비밀번호 검증
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        // 새 비밀번호 저장
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
