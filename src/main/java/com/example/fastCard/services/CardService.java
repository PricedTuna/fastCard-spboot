package com.example.fastCard.services;

import com.example.fastCard.entities.Card;
import com.example.fastCard.repository.CardRepository;
import com.example.fastCard.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    DeckRepository deckRepository;

    public Card studyCard(Long id, boolean isAnswerKnow){
        Optional<Card> optionalCard = getCardById(id);

        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            card.study(isAnswerKnow);
            return cardRepository.save(card);
        } else {
            throw new RuntimeException(("Card not found"));
        }
    }

    public List<Card> getCardsByDeck(Long deckId){
        LocalDateTime now = LocalDateTime.now();
        return cardRepository.findByDeckIdAndStudyAgainBefore(deckId, now);
    }


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
        }).orElseThrow( () -> new RuntimeException("Deck not found") );
    }

    public Card updateCard(Long id, Card newCard) {
        return cardRepository.findById(id).map(card -> {
            card.setFront(newCard.getFront());
            card.setBack(newCard.getBack());
            card.setDeck(newCard.getDeck());
            return cardRepository.save(card);
        }).orElseThrow(() -> new RuntimeException("Card not found with id " + id));
    }

    public void deleteCard(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() -> new RuntimeException("Card not found with id " + id));
        cardRepository.delete(card);
    }

}
