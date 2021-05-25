package com.quizzl.app.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor


@Entity
public class Flashcard extends BaseEntity{

    private String question;
    private String answer;

    @ManyToOne(
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "statistic_id")
    private Statistic statistic;

    @ManyToOne(
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "cardlist_id")
    private FlashcardStaple flashcardList;
}
