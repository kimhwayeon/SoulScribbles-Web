package com.soulscribble.soulscribbles_backend.service;

import com.soulscribble.soulscribbles_backend.domain.Emotion;
import com.soulscribble.soulscribbles_backend.domain.User;
import com.soulscribble.soulscribbles_backend.repository.EmotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmotionService {

    private final EmotionRepository emotionRepository;

    @Autowired
    public EmotionService(EmotionRepository emotionRepository) {
        this.emotionRepository = emotionRepository;
    }

    public void saveEmotion(Emotion emotion) {
        emotionRepository.save(emotion);
    }

    // 오늘 감정 선택했는지 확인
    public boolean hasSelectedTodayEmotion(User user) {
        return emotionRepository.existsByUserIdAndDate(user.getId(), LocalDate.now());
    }

    public Optional<Emotion> getTodayEmotion(User user) {
        return emotionRepository.findTopByUserIdAndDateOrderByCreatedAtDesc(user.getId(), LocalDate.now());
    }


}
