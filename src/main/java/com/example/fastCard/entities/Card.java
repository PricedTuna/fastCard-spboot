package com.example.fastCard.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String front;
    @Column(nullable = false)
    private String back;

    private int studyCount = 0;
    private int hoursToStudyAgain = getHoursToStudyAgain();
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime studyAgain = LocalDateTime.now();

    private static final List<Integer> HOURS_TO_STUDY_AGAIN_VALUES = List.of(1, 24, 72, 120, 240, 360, 720);
    private static final int MAX_STUDY_COUNT = HOURS_TO_STUDY_AGAIN_VALUES.size();
    private static final int MIN_STUDY_COUNT = 0;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;

    public int getHoursToStudyAgain() {
        if (studyCount >= 0 && studyCount < HOURS_TO_STUDY_AGAIN_VALUES.size()) {
            return HOURS_TO_STUDY_AGAIN_VALUES.get(studyCount);
        } else {
            return 0; // default value if studyCount isn't a valid index
        }
    }

    public void setStudyCount(int studyCount) {
        if (studyCount >= 0 && studyCount <= MAX_STUDY_COUNT) {
            this.studyCount = studyCount;
        } else {
            throw new IllegalArgumentException("studyCount must be between 0 and " + MAX_STUDY_COUNT);
        }
    }

    public void study(boolean isAnswerKnow){
        if(isAnswerKnow){
            if(studyCount >= 0){
                studyCount = Math.min(studyCount+1, MAX_STUDY_COUNT);
                studyAgain = studyAgain.plusHours(hoursToStudyAgain);
                hoursToStudyAgain = getHoursToStudyAgain();
            }
        } else {
            studyCount = Math.max(studyCount-2, MIN_STUDY_COUNT);
        }
    }
}
