package com.soulscribble.soulscribbles_backend.controller;

import com.soulscribble.soulscribbles_backend.domain.Emotion;
import com.soulscribble.soulscribbles_backend.domain.User;
import com.soulscribble.soulscribbles_backend.service.EmotionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/emotion")
public class EmotionController {

    private final EmotionService emotionService;

    @Autowired
    public EmotionController(EmotionService emotionService) {
        this.emotionService = emotionService;
    }

    @PostMapping("/select")
    public String selectEmotion(@RequestBody Emotion emotion, HttpSession session) {
        Object user = session.getAttribute("user");
        if (user == null) {
            return "로그인된 사용자만 감정을 선택할 수 있습니다.";
        }

        emotion.setUserId(((User) user).getId());
        emotion.setDate(LocalDate.now());

        System.out.println("감정 저장 요청: " + emotion.getTopEmotion() + " / " + emotion.getDetailEmotion());

        emotionService.saveEmotion(emotion);
        return "감정 저장 완료!";
    }

}

