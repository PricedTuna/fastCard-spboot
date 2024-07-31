package com.example.fastCard.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DeckResponseDto {
    private Long id;
    private String name;
    private List<CardResponseDto> cards;
}
