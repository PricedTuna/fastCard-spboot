package com.example.fastCard.dtos;

import com.example.fastCard.dtos.Cards.CardResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class DeckResponseDto {
    private Long id;
    private String name;
    private List<CardResponseDto> cards;
}
