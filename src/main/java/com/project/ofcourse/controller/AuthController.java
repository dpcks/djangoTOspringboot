package com.project.ofcourse.controller;

import com.project.ofcourse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    // 로그인 페이지
    @GetMapping("/login")
    public String loginForm() {
        return "auth/login";
    }

    // 회원가입 페이지
    @GetMapping("/register")
    public String registerForm() {
        return "auth/register";
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        userService.register(username, password);
        return "redirect:/auth/login";
    }

    // 아이디 중복 확인 API
    @GetMapping("/check-username")
    @ResponseBody
    public ResponseEntity<String> checkUsername(@RequestParam String username) {
        boolean isDuplicate = userService.isUsernameDuplicate(username);
        return ResponseEntity.ok(isDuplicate ? "duplicate" : "available");
    }

    // 회원정보 수정 페이지 반환
    @GetMapping("/edit-profile")
    public String editProfileForm() {
        return "auth/edit_profile";
    }

    // 비밀번호 변경 처리
    @PostMapping("/edit-profile")
    public String editProfile(@AuthenticationPrincipal UserDetails userDetails,
                              @RequestParam String currentPassword,
                              @RequestParam String newPassword,
                              @RequestParam String confirmNewPassword,
                              Model model) {
        // 새 비밀번호와 확인 비밀번호 불일치 검증
        if (!newPassword.equals(confirmNewPassword)) {
            model.addAttribute("errorMessage", "새 비밀번호가 일치하지 않습니다.");
            return "auth/edit_profile";
        }

        try {
            // 현재 비밀번호 검증 및 비밀번호 업데이트
            userService.updatePassword(userDetails.getUsername(), currentPassword, newPassword);
            model.addAttribute("successMessage", "비밀번호가 성공적으로 변경되었습니다.");
        } catch (IllegalArgumentException e) {
            // 현재 비밀번호가 틀린 경우
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "auth/edit_profile";
    }

}
