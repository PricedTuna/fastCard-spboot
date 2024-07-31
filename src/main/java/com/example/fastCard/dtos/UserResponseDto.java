package com.example.fastCard.dtos;

import com.example.fastCard.entities.Deck;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private List<DeckResponseDto> decks;
}
