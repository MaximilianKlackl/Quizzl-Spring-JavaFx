package com.quizzl.app.model.db;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString

@Entity
@DiscriminatorValue("FlashcardStaple")
public class FlashcardStaple extends CardList {

    private String topic;

    @OneToMany(
            mappedBy = "flashcardList",
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER) // leave FetchType.Eager or FlashcardsController gonna break...
    private List<Flashcard> flashcardList;

    public FlashcardStaple(String topic, String name, String description){
        super(name, description);
        flashcardList = new ArrayList<>();
        this.topic = topic;
    }

    public FlashcardStaple(String topic, String name, String description, List<Flashcard> flashcardList, Statistic statistic){
        super(name, description, statistic);
        this.flashcardList = flashcardList;
        this.topic = topic;
    }

    public FlashcardStaple() {

    }
}
