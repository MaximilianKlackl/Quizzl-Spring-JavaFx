package com.quizzl.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor @AllArgsConstructor

@Entity
public class Statistic extends BaseEntity {

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private FlashcardStaple cardList;

    @OneToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name="FLASHCARD_LIST_ID")
    private List<Flashcard> wrongCards;

    private int timeSpend; // in minutes
    private float learnProgress; // in percent
}
