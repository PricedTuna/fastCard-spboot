package com.example.fastCard.controllers;

import com.example.fastCard.dtos.Cards.CardCreateDto;
import com.example.fastCard.dtos.Cards.CardResponseDto;
import com.example.fastCard.dtos.Cards.CardStudyDto;
import com.example.fastCard.entities.Card;
import com.example.fastCard.mappers.card.CardCreateMapper;
import com.example.fastCard.mappers.card.CardResponseMapper;
import com.example.fastCard.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping
    public List<CardResponseDto> getAllCards() {
        return CardResponseMapper.toCardDtoList(cardService.getAllCards());
    }

    @GetMapping("/deck/{deckId}")
    public List<CardResponseDto> getCardsByDeck(@PathVariable Long deckId){
        List<Card> cards = cardService.getCardsByDeck(deckId);
        return CardResponseMapper.toCardDtoList(cards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResponseDto> getCardById(@PathVariable Long id) {
        Optional<Card> cardById = cardService.getCardById(id);
        return cardById.map(card -> ResponseEntity.ok(CardResponseMapper.toCardDTO(card)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CardResponseDto createCard(@RequestBody CardCreateDto cardCreateDto) {
        Long deckId = cardCreateDto.getDeck().getId();
        return CardResponseMapper.toCardDTO(cardService.createCard(deckId, CardCreateMapper.toCardDto(cardCreateDto)));
    }

    @PostMapping("/study")
    public CardResponseDto studyCard(@RequestBody CardStudyDto cardStudyDto){
        Card card = cardService.studyCard(cardStudyDto.getCardId(), cardStudyDto.isAnswerKnow());
        return CardResponseMapper.toCardDTO(card);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @RequestBody Card cardDetails) {
        try {
            Card updatedCard = cardService.updateCard(id, cardDetails);
            return ResponseEntity.ok(updatedCard);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        try {
            cardService.deleteCard(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
