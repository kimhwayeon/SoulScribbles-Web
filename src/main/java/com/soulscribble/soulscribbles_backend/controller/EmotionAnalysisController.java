package com.soulscribble.soulscribbles_backend.controller;

import com.soulscribble.soulscribbles_backend.service.EmotionAnalysisService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/emotion")
public class EmotionAnalysisController {

    private final EmotionAnalysisService emotionAnalysisService;

    public EmotionAnalysisController(EmotionAnalysisService emotionAnalysisService) {
        this.emotionAnalysisService = emotionAnalysisService;
    }

    @GetMapping("/analysis")
    public ResponseEntity<?> getEmotionStatistics(HttpSession session) {
        Long userId = (Long) session.getAttribute("loginUserId"); // userId로 꺼냄
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        Map<String, Integer> result = emotionAnalysisService.analyzeEmotionByUserId(userId);
        return ResponseEntity.ok(result);
    }

}
