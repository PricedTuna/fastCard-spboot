package com.example.fastCard.mappers;

import com.example.fastCard.dtos.DeckResponseDto;
import com.example.fastCard.entities.Deck;
import com.example.fastCard.mappers.card.CardResponseMapper;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DeckResponseMapper {

    public static List<DeckResponseDto> toDeckDtoList(List<Deck> decks){
        if(decks == null){
            return null;
        }

        return decks.stream().map(DeckResponseMapper::toDto).collect(Collectors.toList());
    }

    public static DeckResponseDto toDto(Deck deck){

        if(deck == null){
            return null;
        }

        DeckResponseDto deckResponseDto = new DeckResponseDto();
        deckResponseDto.setId(deck.getId());
        deckResponseDto.setName(deck.getName());
        deckResponseDto.setCards( CardResponseMapper.toCardDtoList(deck.getCards()) );

        return deckResponseDto;
    }

}
