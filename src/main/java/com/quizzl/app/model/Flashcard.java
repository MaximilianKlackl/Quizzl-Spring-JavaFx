package com.quizzl.app.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString

@Entity
public class Flashcard extends Card{

    @ManyToOne(
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "cardlist_id")
    private FlashcardStaple flashcardList;

    public Flashcard(String back, String front){
        super(back, front);
    }

    public Flashcard(){

    }
}
