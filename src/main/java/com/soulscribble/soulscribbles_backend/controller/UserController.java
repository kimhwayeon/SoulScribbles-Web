package com.soulscribble.soulscribbles_backend.controller;

import com.soulscribble.soulscribbles_backend.domain.User;
import com.soulscribble.soulscribbles_backend.dto.UserResponse;
import com.soulscribble.soulscribbles_backend.service.EmotionService;
import com.soulscribble.soulscribbles_backend.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final EmotionService emotionService;

    @Autowired
    public UserController(UserService userService, EmotionService emotionService) {
        this.userService = userService;
        this.emotionService = emotionService;
    }

    // 회원가입
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userService.isEmailDuplicated(user.getEmail())) {
            return "이미 사용 중인 이메일입니다.";
        }
        user.setJoinDate(LocalDate.now()); // 회원가입 날짜 저장
        userService.register(user);
        return "회원가입 성공!";
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser, HttpSession session) {
        Optional<User> userOptional = userService.findByEmail(loginUser.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String rawPassword = loginUser.getPassword();
            String storedPassword = user.getPassword();

            if (rawPassword != null && rawPassword.equals(storedPassword)) {
                session.setAttribute("user", user);
                session.setAttribute("loginUserId", user.getId());

                boolean hasEmotionToday = emotionService.hasSelectedTodayEmotion(user);

                String redirectUrl = hasEmotionToday ? "Home.html" : "select-emotion.html";
                return ResponseEntity.ok(Map.of("redirect", redirectUrl));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "이메일 또는 비밀번호가 일치하지 않습니다."));
    }




    // 내 정보 조회 (Profile 페이지)
    @GetMapping("/me")
    public UserResponse getMyProfile(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new IllegalStateException("로그인하지 않은 사용자입니다.");
        }

        return new UserResponse(user.getName(), user.getEmail(), user.getJoinDate());
    }

    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "로그아웃 성공!";
    }
}
