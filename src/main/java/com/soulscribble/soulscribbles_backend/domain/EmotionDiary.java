package com.soulscribble.soulscribbles_backend.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class EmotionDiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    private String title;

    private LocalDate date;

    private String emotion;

    private String reason;

    private String thought;

    private String expression;

    private String audioUrl;

    private String topEmotion;  // 상위 감정

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;  // ✨ 작성 시간 자동 생성

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 날짜 자동 설정용 메서드
    @PrePersist
    public void prePersist() {
        if (this.date == null) {
            this.date = LocalDate.now();  // date가 null이면 오늘 날짜로 저장
        }
    }

    // Getter/Setter
    public Long getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Long diaryId) {
        this.diaryId = diaryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getThought() {
        return thought;
    }

    public void setThought(String thought) {
        this.thought = thought;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTopEmotion() {
        return topEmotion;
    }

    public void setTopEmotion(String topEmotion) {
        this.topEmotion = topEmotion;
    }
}
