package com.soulscribble.soulscribbles_backend.dto;

public class EmotionDiaryDto {
    private Long diaryId;
    private String title;
    private String emotion;
    private String date;

    private String reason;
    private String thought;
    private String expression;
    private String audioUrl;

    // 목록 조회용 생성자
    public EmotionDiaryDto(Long diaryId, String title, String emotion, String date) {
        this.diaryId = diaryId;
        this.title = title;
        this.emotion = emotion;
        this.date = date;
    }

    // 상세 조회용 생성자
    public EmotionDiaryDto(Long diaryId, String title, String emotion, String date,
                           String reason, String thought, String expression, String audioUrl) {
        this.diaryId = diaryId;
        this.title = title;
        this.emotion = emotion;
        this.date = date;
        this.reason = reason;
        this.thought = thought;
        this.expression = expression;
        this.audioUrl = audioUrl;
    }

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

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
