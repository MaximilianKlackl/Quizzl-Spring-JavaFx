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
    private Statistic statistic;

    @ManyToOne(
            cascade = {CascadeType.MERGE},
            fetch = FetchType.LAZY)
    private FlashcardStaple flashcardList;
}
