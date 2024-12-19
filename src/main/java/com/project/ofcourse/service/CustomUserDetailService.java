package com.project.ofcourse.service;

import com.project.ofcourse.entity.User;
import com.project.ofcourse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    // 사용자 정보를 제공하는 서비스 username을 기반으로 데이터베이스나 기타 저장소에서 사용자를 조회하고, 해당 정보를 Spring Security가 이해할 수
    // 있는 형태로 변환해 반환한다.

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.: " + username);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) //암호화된 비밀번호
                .authorities(user.getRole()) // authorities 사용
                .build();
    }
}
