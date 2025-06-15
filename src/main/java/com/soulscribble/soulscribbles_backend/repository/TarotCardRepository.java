package com.soulscribble.soulscribbles_backend.repository;

import com.soulscribble.soulscribbles_backend.domain.TarotCard;
import com.soulscribble.soulscribbles_backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TarotCardRepository extends JpaRepository<TarotCard, Long> {

    // 오늘 해당 유저가 이미 뽑았는지 확인용
    Optional<TarotCard> findByUserAndDrawDate(User user, LocalDate drawDate);

    // 해당 유저의 모든 타로 기록 조회
    List<TarotCard> findAllByUserOrderByDrawDateDesc(User user);
}
