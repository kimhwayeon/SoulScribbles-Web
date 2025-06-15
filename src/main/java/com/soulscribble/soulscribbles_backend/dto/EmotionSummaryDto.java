package com.soulscribble.soulscribbles_backend.dto;

public class EmotionSummaryDto {
    private int count;               // 총 감정일기 개수
    private String latestEmotion;    // 가장 최근 감정 (세부감정)
    private String topEmotion;       // 가장 최근 감정의 상위감정

    // 생성자
    public EmotionSummaryDto(int count, String latestEmotion, String topEmotion) {
        this.count = count;
        this.latestEmotion = latestEmotion;
        this.topEmotion = topEmotion;
    }

    // 기본 생성자 (필요 시)
    public EmotionSummaryDto() {
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getLatestEmotion() {
        return latestEmotion;
    }
    public void setLatestEmotion(String latestEmotion) {
        this.latestEmotion = latestEmotion;
    }
    public String getTopEmotion() {
        return topEmotion;
    }
    public void setTopEmotion(String topEmotion) {
        this.topEmotion = topEmotion;
    }
}
