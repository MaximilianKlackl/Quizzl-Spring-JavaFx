package com.quizzl.app.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@ToString
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Statistic extends BaseEntity {

    @OneToOne(
            mappedBy = "statistic",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private CardList cardList;

    @OneToMany(
            mappedBy = "statistic",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    private List<Card> wrongCards;

    private int timeSpend; // in minutes
    private float learnProgress; // in percent

    public Statistic(int timeSpend, float learnProgress){
        this.timeSpend = timeSpend;
        this.learnProgress = learnProgress;
    }

    public Statistic(int timeSpend, float learnProgress, List<Card> wrongCards, CardList cardList){
        this.timeSpend = timeSpend;
        this.cardList = cardList;
        this.learnProgress = learnProgress;
        this.wrongCards = wrongCards;
    }

    public Statistic(){

    }
}
