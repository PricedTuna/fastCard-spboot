package com.example.fastCard.dtos.Cards;

import com.example.fastCard.entities.Deck;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CardCreateDto extends CardBase{
    Deck deck;
}
