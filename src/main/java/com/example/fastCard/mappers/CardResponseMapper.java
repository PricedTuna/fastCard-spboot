package com.example.fastCard.mappers;

import com.example.fastCard.dtos.CardResponseDto;
import com.example.fastCard.entities.Card;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CardResponseMapper {

    public static List<CardResponseDto> toCardDtoList(List<Card> cards){
        if(cards == null){
            return null;
        }

        return cards.stream().map(CardResponseMapper::toCardDTO).collect(Collectors.toList());
    }

    public static CardResponseDto toCardDTO(Card card){
        if (card == null) {
            return null;
        }

        CardResponseDto cardDTO = new CardResponseDto();
        cardDTO.setId(card.getId());
        cardDTO.setFront(card.getFront());
        cardDTO.setBack(card.getBack());
        cardDTO.setStudied(card.isStudied());

        return cardDTO;
    }

}
