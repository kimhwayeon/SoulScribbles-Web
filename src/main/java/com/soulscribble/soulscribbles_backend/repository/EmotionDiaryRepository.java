package com.soulscribble.soulscribbles_backend.repository;

import com.soulscribble.soulscribbles_backend.domain.EmotionDiary;
import com.soulscribble.soulscribbles_backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmotionDiaryRepository extends JpaRepository<EmotionDiary, Long> {

    // 특정 사용자(userId)에 해당하는 감정일기 목록 조회 (최신 작성일 → 최근 createdAt 순)
    List<EmotionDiary> findByUser_IdOrderByDateDescCreatedAtDesc(Long userId);

    // 특정 일기 ID와 사용자 ID로 감정일기 단건 조회
    Optional<EmotionDiary> findByDiaryIdAndUser_Id(Long diaryId, Long userId);
    long countByUserId(Long userId);

    List<EmotionDiary> findByUser_Id(Long userId);


}
