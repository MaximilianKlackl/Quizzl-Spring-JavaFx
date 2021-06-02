package com.quizzl.app.model.dbEntities;

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

    private String name;
    private String description;
    private String topic;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "STATISTIC_ID")
    private Statistic statistic;

    @OneToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER)
    @JoinColumn(name="FLASHCARD_LIST_ID")
    private List<Flashcard> flashcardList;
}
