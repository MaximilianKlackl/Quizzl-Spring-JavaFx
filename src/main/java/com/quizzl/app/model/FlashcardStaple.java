package com.quizzl.app.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString

@Entity
public class FlashcardStaple extends CardList {

    private String topic;

    @OneToMany(
            mappedBy = "flashcardList",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    private List<Flashcard> flashcardList;

    public FlashcardStaple(String topic, String name, String description){
        super(name, description);
        flashcardList = new ArrayList<>();
        this.topic = topic;
    }

    public FlashcardStaple() {

    }
}
