package com.soulscribble.soulscribbles_backend.service;

import com.soulscribble.soulscribbles_backend.domain.EmotionDiary;
import com.soulscribble.soulscribbles_backend.domain.User;
import com.soulscribble.soulscribbles_backend.dto.EmotionDiaryDto;
import com.soulscribble.soulscribbles_backend.dto.EmotionSummaryDto;
import com.soulscribble.soulscribbles_backend.dto.SaveEmotionDiaryRequest;
import com.soulscribble.soulscribbles_backend.repository.EmotionDiaryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmotionDiaryService {

    private final EmotionDiaryRepository diaryRepository;

    public EmotionDiaryService(EmotionDiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }
    public long countByUser(User user) {
        return diaryRepository.countByUserId(user.getId());
    }


    // 감정일기 목록 조회 (최신 날짜 + 작성 시간 순 정렬)
    public List<EmotionDiaryDto> getEmotionDiaries(Long userId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return diaryRepository.findByUser_IdOrderByDateDescCreatedAtDesc(userId).stream()
                .map(diary -> new EmotionDiaryDto(
                        diary.getDiaryId(),
                        diary.getTitle(),
                        diary.getEmotion(),
                        diary.getDate().format(formatter)))
                .collect(Collectors.toList());
    }

    // 감정일기 저장
    public void saveEmotionDiary(SaveEmotionDiaryRequest request, User user) {
        EmotionDiary diary = new EmotionDiary();
        diary.setTitle(request.getTitle());

        diary.setEmotion(request.getEmotion());
        diary.setTopEmotion(classifyTopEmotion(request.getEmotion()));
        diary.setReason(request.getReason());
        diary.setThought(request.getThought());
        diary.setExpression(request.getExpression());
        diary.setAudioUrl(request.getAudioUrl());
        diary.setUser(user);

        diaryRepository.save(diary);
    }

    // 감정일기 단건 조회
    public EmotionDiaryDto getEmotionDiaryById(Long id, User user) {
        EmotionDiary diary = diaryRepository.findByDiaryIdAndUser_Id(id, user.getId())
                .orElseThrow(() -> new RuntimeException("일기를 찾을 수 없습니다."));

        return new EmotionDiaryDto(
                diary.getDiaryId(),
                diary.getTitle(),
                diary.getEmotion(),
                diary.getDate().toString(),
                diary.getReason(),
                diary.getThought(),
                diary.getExpression(),
                diary.getAudioUrl()
        );
    }

    // 감정일기 삭제
    public void deleteEmotionDiary(Long diaryId, User user) {
        EmotionDiary diary = diaryRepository.findByDiaryIdAndUser_Id(diaryId, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("일기를 찾을 수 없습니다."));

        if (!diary.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인의 일기만 삭제할 수 있습니다.");
        }

        diaryRepository.delete(diary);
    }
    // 감정일기 수정
    public void updateEmotionDiary(Long id, SaveEmotionDiaryRequest request, User user) {
        EmotionDiary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일기를 찾을 수 없습니다."));

        if (!diary.getUser().getId().equals(user.getId())) {
            throw new SecurityException("수정 권한이 없습니다.");
        }

        diary.setTitle(request.getTitle());
        diary.setEmotion(request.getEmotion());
        diary.setReason(request.getReason());
        diary.setThought(request.getThought());
        diary.setExpression(request.getExpression());
        diary.setAudioUrl(request.getAudioUrl());

        diaryRepository.save(diary);
    }


    // 감정 요약 정보 제공
    public EmotionSummaryDto getEmotionSummary(User user) {
        List<EmotionDiary> diaries = diaryRepository.findByUser_IdOrderByDateDescCreatedAtDesc(user.getId());
        int count = diaries.size();

        if (count == 0) {
            return new EmotionSummaryDto(0, null, null);
        }

        EmotionDiary latest = diaries.get(0); // 최신 일기 기준
        return new EmotionSummaryDto(count, latest.getEmotion(), latest.getTopEmotion());
    }

    private String classifyTopEmotion(String detailEmotion) {
        if (detailEmotion == null) return "기타";

        detailEmotion = detailEmotion.trim();

        switch (detailEmotion) {
            case "만족": case "즐거움": case "유쾌함": case "황홀함":
            case "기쁨": case "열정": case "설렘": case "재미":
            case "신남": case "희망": case "편안함": case "감사함":
                return "행복";
            case "매력": case "동정": case "흠모": case "우애":
            case "욕망": case "심취": case "다정다감": case "갈망":
            case "흥분": case "연민": case "좋아함": case "감동":
                return "사랑";
            case "실망": case "심란함": case "꺾임": case "우울":
            case "비참": case "불행": case "괴로움": case "절망":
            case "소외": case "서러움": case "패배감": case "외로움":
                return "슬픔";
            case "화남": case "복수심": case "심술남": case "속상함":
            case "짜증": case "안달남":
                return "분노";
            case "역겨움": case "싫음": case "불쾌함": case "반감":
            case "경멸": case "지긋지긋함":
                return "혐오";
            case "공포": case "충격": case "불안": case "놀람":
                return "두려움";
            case "열망": case "씁쓸함": case "불만족": case "시기":
            case "언짢음":
                return "부러움";
            case "집착": case "경쟁심": case "불신": case "소유욕":
                return "질투";
            case "굴욕": case "부끄러움": case "당혹감":
                return "수치감";
            case "미안함": case "후회": case "안쓰러움": case "비난받는 느낌":
                return "죄책감";
            default: return "기타";
        }
    }

}
