package com.soulscribble.soulscribbles_backend.service;

import com.soulscribble.soulscribbles_backend.domain.User;
import com.soulscribble.soulscribbles_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입
    public void register(User user) {
        if (!user.isFirstLogin()) {
            user.setFirstLogin(true);
        }
        userRepository.save(user);
    }



    // 이메일 중복 확인
    public boolean isEmailDuplicated(String email) {
        return userRepository.existsByEmail(email);
    }

    // 이메일로 사용자 찾기 (로그인용)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
