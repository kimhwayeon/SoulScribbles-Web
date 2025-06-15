package com.soulscribble.soulscribbles_backend.repository;
import com.soulscribble.soulscribbles_backend.domain.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
    boolean existsByUserIdAndDate(Long userId, LocalDate date);

    Optional<Emotion> findTopByUserIdAndDateOrderByCreatedAtDesc(Long userId, LocalDate date);
}
