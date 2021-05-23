package com.quizzl.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class FlashcardStaple extends BaseEntity{

    private String topic;
    private String name;
    private String description;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "statistic_id")
    private Statistic statistic;

    @OneToMany(
            mappedBy = "flashcardList",
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER) // leave FetchType.Eager or FlashcardsController gonna break...
    private List<Flashcard> flashcardList;

}
