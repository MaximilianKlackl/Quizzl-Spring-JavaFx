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
            mappedBy = "statistic",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private FlashcardStaple cardList;

    @OneToMany(
            mappedBy = "statistic",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    private List<Flashcard> wrongCards;

    private int timeSpend; // in minutes
    private float learnProgress; // in percent
}
