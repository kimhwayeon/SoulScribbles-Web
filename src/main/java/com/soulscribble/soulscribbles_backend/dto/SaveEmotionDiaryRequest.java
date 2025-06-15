package com.soulscribble.soulscribbles_backend.dto;

import java.util.Date;

public class SaveEmotionDiaryRequest {
    private String title;
    private String date;
    private String emotion;
    private String reason;
    private String thought;
    private String expression;
    private String audioUrl;

    // Getter & Setter 수동 구현
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
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
}
