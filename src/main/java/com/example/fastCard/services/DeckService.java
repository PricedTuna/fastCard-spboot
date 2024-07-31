package com.example.fastCard.services;

import com.example.fastCard.entities.Card;
import com.example.fastCard.entities.Deck;
import com.example.fastCard.repository.DeckRepository;
import com.example.fastCard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeckService {

    @Autowired
    DeckRepository deckService;

    @Autowired
    UserRepository userRepository;

    public List<Deck> getAllDecks(){
        return deckService.findAll();
    }

    public Optional<Deck> getDeckById(Long id){
        return deckService.findById(id);
    }

    public Deck createDeck(Long userId, Deck deck){
        return  userRepository.findById(userId).map(user -> {
            deck.setUser(user);
            return deckService.save(deck);
        }).orElseThrow(() -> new RuntimeException("User with "+userId+" not found") );
    }

    public void deleteDeck(Long id){
        deckService.deleteById(id);
    }

}
