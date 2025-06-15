package com.soulscribble.soulscribbles_backend.service;

import com.soulscribble.soulscribbles_backend.domain.TarotCard;
import com.soulscribble.soulscribbles_backend.domain.User;
import com.soulscribble.soulscribbles_backend.repository.TarotCardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TarotService {

    private final TarotCardRepository tarotCardRepository;

    // 생성자 직접 작성
    public TarotService(TarotCardRepository tarotCardRepository) {
        this.tarotCardRepository = tarotCardRepository;
    }

    // 오늘 뽑은 카드 저장
    public void saveCard(User user, int ignoredClientCardNumber) {
        LocalDate today = LocalDate.now();

        // 이미 뽑았는지 확인
        tarotCardRepository.findByUserAndDrawDate(user, today)
                .ifPresent(card -> {
                    throw new IllegalStateException("오늘은 이미 타로 카드를 뽑았습니다.");
                });

        // 서버에서 1~20 중 무작위 선택
        int randomCardNumber = (int)(Math.random() * 20) + 1;

        // 저장
        TarotCard card = new TarotCard(randomCardNumber, today, user);
        tarotCardRepository.save(card);
    }


    // 유저별 타로 카드 기록 조회
    public List<TarotCard> findCardsByUser(User user) {
        return tarotCardRepository.findAllByUserOrderByDrawDateDesc(user);
    }

    // 오늘의 카드 조회
    public Optional<TarotCard> findTodayCard(User user) {
        return tarotCardRepository.findByUserAndDrawDate(user, LocalDate.now());
    }
}
