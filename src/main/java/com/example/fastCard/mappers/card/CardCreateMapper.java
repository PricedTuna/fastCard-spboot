package com.example.fastCard.mappers.card;

import com.example.fastCard.dtos.Cards.CardCreateDto;
import com.example.fastCard.dtos.Cards.CardResponseDto;
import com.example.fastCard.entities.Card;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CardCreateMapper {

    public static List<CardResponseDto> toCardDtoList(List<Card> cards){
        if(cards == null){
            return null;
        }

        return cards.stream().map(CardResponseMapper::toCardDTO).collect(Collectors.toList());
    }


    public static Card toCardDto(CardCreateDto cardCreateDto){

        Card card = new Card();

        card.setFront(cardCreateDto.getFront());
        card.setBack(cardCreateDto.getBack());
        card.setDeck(cardCreateDto.getDeck());

        return card;

    }

}
