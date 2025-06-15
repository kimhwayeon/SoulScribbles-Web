package com.soulscribble.soulscribbles_backend.controller;

import com.soulscribble.soulscribbles_backend.domain.TarotCard;
import com.soulscribble.soulscribbles_backend.domain.User;
import com.soulscribble.soulscribbles_backend.dto.TarotCardResponseDto;
import com.soulscribble.soulscribbles_backend.service.TarotService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarot")
public class TarotController {

    private final TarotService tarotService;

    // 생성자
    public TarotController(TarotService tarotService) {
        this.tarotService = tarotService;
    }

    // 오늘 카드 저장
    @PostMapping("/draw")
    public ResponseEntity<?> saveCard(@RequestBody TarotCardResponseDto request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");

        try {
            tarotService.saveCard(user, request.getCardNumber());
            return ResponseEntity.ok("오늘의 타로 카드가 저장되었습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 타로 기록 전체 조회
    @GetMapping("/history")
    public ResponseEntity<?> getHistory(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");

        List<TarotCard> history = tarotService.findCardsByUser(user);
        return ResponseEntity.ok(history);
    }

    // 오늘 카드 조회 (있으면 반환, 없으면 204 No Content)
    @GetMapping("/today")
    public ResponseEntity<?> getTodayCard(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");

        Optional<TarotCard> card = tarotService.findTodayCard(user);
        return card.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
