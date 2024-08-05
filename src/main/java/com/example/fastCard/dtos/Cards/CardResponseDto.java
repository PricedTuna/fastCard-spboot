package com.example.fastCard.dtos.Cards;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class CardResponseDto extends CardBase {
    private Long id;
    private LocalDateTime studyAgain;
}
