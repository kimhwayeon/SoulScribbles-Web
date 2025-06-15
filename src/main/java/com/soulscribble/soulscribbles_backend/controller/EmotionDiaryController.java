package com.soulscribble.soulscribbles_backend.controller;

import com.soulscribble.soulscribbles_backend.domain.User;
import com.soulscribble.soulscribbles_backend.dto.EmotionDiaryDto;
import com.soulscribble.soulscribbles_backend.dto.EmotionSummaryDto;
import com.soulscribble.soulscribbles_backend.dto.SaveEmotionDiaryRequest;
import com.soulscribble.soulscribbles_backend.repository.EmotionDiaryRepository;
import com.soulscribble.soulscribbles_backend.service.EmotionDiaryService;
import com.soulscribble.soulscribbles_backend.service.EmotionService;
import com.soulscribble.soulscribbles_backend.domain.Emotion;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/emotion-diary")
public class EmotionDiaryController {

    private final EmotionDiaryService emotionDiaryService;
    private final EmotionService emotionService;
    private final EmotionDiaryRepository diaryRepository;

    public EmotionDiaryController(EmotionDiaryService emotionDiaryService, EmotionService emotionService, EmotionDiaryRepository diaryRepository) {
        this.emotionDiaryService = emotionDiaryService;
        this.emotionService = emotionService;
        this.diaryRepository = diaryRepository;
    }

    // 전체 일기 목록 조회
    @GetMapping("/list")
    public ResponseEntity<?> getEmotionDiaries(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }
        List<EmotionDiaryDto> diaries = emotionDiaryService.getEmotionDiaries(user.getId());
        return ResponseEntity.ok(diaries);
    }

    // 일기 저장
    @PostMapping("/save")
    public ResponseEntity<?> saveEmotionDiary(@RequestBody SaveEmotionDiaryRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }

        try {
            emotionDiaryService.saveEmotionDiary(request, user);
            return ResponseEntity.ok("저장 완료!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("잘못된 요청: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 발생: " + e.getMessage());
        }
    }

    // 단일 일기 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getDiaryById(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }

        EmotionDiaryDto diary = emotionDiaryService.getEmotionDiaryById(id, user);
        if (diary == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("일기를 찾을 수 없습니다.");
        }
        return ResponseEntity.ok(diary);
    }

    // 일기 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiary(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }

        try {
            emotionDiaryService.deleteEmotionDiary(id, user);
            return ResponseEntity.ok("삭제 완료");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("삭제 실패: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류: " + e.getMessage());
        }
    }

    // 감정 요약 조회 (프로필용)
    @GetMapping("/summary")
    public ResponseEntity<?> getSummary(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");

        long count = emotionDiaryService.countByUser(user);

        String currentEmotion = emotionService.getTodayEmotion(user)
                .map(Emotion::getDetailEmotion)
                .orElse("없음");

        Map<String, Object> result = new HashMap<>();
        result.put("count", count);
        result.put("latestEmotion", currentEmotion);
        return ResponseEntity.ok(result);
    }

    // 일기 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmotionDiary(@PathVariable Long id,
                                                @RequestBody SaveEmotionDiaryRequest request,
                                                HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }

        try {
            emotionDiaryService.updateEmotionDiary(id, request, user);
            return ResponseEntity.ok("수정 완료!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수정 실패: " + e.getMessage());
        }
    }

}
