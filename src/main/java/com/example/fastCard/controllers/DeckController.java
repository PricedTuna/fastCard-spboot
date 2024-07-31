package com.example.fastCard.controllers;

import com.example.fastCard.dtos.DeckResponseDto;
import com.example.fastCard.entities.Deck;
import com.example.fastCard.mappers.DeckResponseMapper;
import com.example.fastCard.services.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/deck")
public class DeckController {

    @Autowired
    DeckService deckService;

    @GetMapping
    public List<DeckResponseDto> getAllDecks(){
        return DeckResponseMapper.toDeckDtoList(deckService.getAllDecks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeckResponseDto> getDeckById(@PathVariable Long id){
        Optional<Deck> deckById = deckService.getDeckById(id);
        return deckById.map(deck -> ResponseEntity.ok(DeckResponseMapper.toDto(deck)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DeckResponseDto createDeck(@RequestBody Deck deck){
        Long userId = deck.getUser().getId();
        return DeckResponseMapper.toDto(deckService.createDeck(userId, deck));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeck(@PathVariable Long id){
        try {
            deckService.deleteDeck(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return  ResponseEntity.notFound().build();
        }
    }

}
