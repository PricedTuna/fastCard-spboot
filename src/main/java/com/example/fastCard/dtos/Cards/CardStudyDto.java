package com.example.fastCard.dtos.Cards;

import lombok.Data;

@Data
public class CardStudyDto {
    private Long cardId;
    private boolean isAnswerKnow = true;
}
