package com.project.ofcourse.config;

import com.project.ofcourse.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailService userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //SecurityFilterChani을 사용해 보안 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/auth/**", "/css/**", "/js/**", "/img/**").permitAll()
                        .anyRequest().authenticated()) // 나머지 요청은 인증 필요
                .formLogin(form -> form
                        .loginPage("/auth/login") //로그인 페이지
                        .defaultSuccessUrl("/", true) // 로그인 성공 시 메인 페이지로 이동
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // GET 방식 로그아웃 허용
                        .logoutSuccessUrl("/") // 로그아웃 성공 후 이동할 URL
                        .permitAll())
                .userDetailsService(userDetailService); // userDetailService 등록

        return http.build();
    }
}
