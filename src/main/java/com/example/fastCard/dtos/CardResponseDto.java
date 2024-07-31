package com.example.fastCard.dtos;

import lombok.Data;

@Data
public class CardResponseDto {
    private Long id;
    private String front;
    private String back;
    private boolean isStudied;
}
