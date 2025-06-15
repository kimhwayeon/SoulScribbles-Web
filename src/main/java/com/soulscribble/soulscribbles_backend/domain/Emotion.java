package com.soulscribble.soulscribbles_backend.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Emotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 연관관계 없이 userId만 저장
    private Long userId;

    private String topEmotion;

    private String detailEmotion;

    // 오늘 감정 선택 여부 확인용
    private LocalDate date;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getter & Setter
    public Long getId() { return id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getTopEmotion() { return topEmotion; }
    public void setTopEmotion(String topEmotion) { this.topEmotion = topEmotion; }

    public String getDetailEmotion() { return detailEmotion; }
    public void setDetailEmotion(String detailEmotion) { this.detailEmotion = detailEmotion; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
