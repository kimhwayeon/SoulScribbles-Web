package com.soulscribble.soulscribbles_backend.service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.soulscribble.soulscribbles_backend.domain.EmotionDiary;
import com.soulscribble.soulscribbles_backend.domain.User;
import com.soulscribble.soulscribbles_backend.repository.EmotionDiaryRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmotionAnalysisService {

    private final EmotionDiaryRepository emotionDiaryRepository;

    public EmotionAnalysisService(EmotionDiaryRepository emotionDiaryRepository) {
        this.emotionDiaryRepository = emotionDiaryRepository;
    }

    public Map<String, Integer> analyzeEmotionByUserId(Long userId) {
        List<EmotionDiary> diaries = emotionDiaryRepository.findByUser_Id(userId);  // ID 기반 조회

        Map<String, Integer> result = new HashMap<>();
        for (EmotionDiary diary : diaries) {
            String topEmotion = diary.getTopEmotion();
            if (topEmotion == null || topEmotion.isBlank()) continue;
            result.put(topEmotion, result.getOrDefault(topEmotion, 0) + 1);

        }

        return result;
    }

}
