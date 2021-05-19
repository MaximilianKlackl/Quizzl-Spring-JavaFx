package com.quizzl.app.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)

@Entity
public class Flashcard extends Card{

    @ManyToOne(
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "cardlist_id")
    private FlashcardStaple flashcardList;

    public Flashcard(String front, String back){
        super(back, front);
    }

    public Flashcard(String front, String back, Statistic statistic, FlashcardStaple flashcardList){
        super(back, front, statistic);
        this.flashcardList = flashcardList;
    }

    public Flashcard(){

    }
}
