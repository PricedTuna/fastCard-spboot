package com.example.fastCard.repository;

import com.example.fastCard.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByDeckIdAndStudyAgainBefore(Long deckId, LocalDateTime currentDate);

}
