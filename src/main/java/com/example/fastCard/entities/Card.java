package com.example.fastCard.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String front;
    private String back;
    private boolean isStudied;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;
}
