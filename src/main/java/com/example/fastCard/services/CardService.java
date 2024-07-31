package com.example.fastCard.services;

import com.example.fastCard.entities.Card;
import com.example.fastCard.repository.CardRepository;
import com.example.fastCard.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    DeckRepository deckRepository;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
    }

    public Card createCard(Long deckId, Card card) {
        return deckRepository.findById(deckId).map(deck -> {
            card.setDeck(deck);
            return cardRepository.save(card);
        }).orElseThrow( () -> new RuntimeException("Deck with "+deckId+" not found") );
    }

    public Card updateCard(Long id, Card newCard) {
        return cardRepository.findById(id).map(card -> {
            card.setFront(newCard.getFront());
            card.setBack(newCard.getBack());
            card.setStudied(newCard.isStudied());
            card.setDeck(newCard.getDeck());
            return cardRepository.save(card);
        }).orElseThrow(() -> new RuntimeException("Card not found with id " + id));
    }

    public void deleteCard(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() -> new RuntimeException("Card not found with id " + id));
        cardRepository.delete(card);
    }

}
