package com.soulscribble.soulscribbles_backend.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class TarotCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cardNumber;
    private LocalDate drawDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public TarotCard() {}

    public TarotCard(int cardNumber, LocalDate drawDate, User user) {
        this.cardNumber = cardNumber;
        this.drawDate = drawDate;
        this.user = user;
    }

    // Getter/Setter
    public Long getId() {
        return id;
    }
    public int getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
    public LocalDate getDrawDate() {
        return drawDate;
    }
    public void setDrawDate(LocalDate drawDate) {
        this.drawDate = drawDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
