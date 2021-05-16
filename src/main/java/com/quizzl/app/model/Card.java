package com.quizzl.app.model.db;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
@Setter

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype",
        discriminatorType = DiscriminatorType.STRING)
public class Card extends BaseEntity {

    private String front;
    private String back;

    @ManyToOne(
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "statistic_id")
    private Statistic statistic;

    public Card(String front, String back){

        this.front = front;
        this.back = back;
    }

    public Card(String front, String back, Statistic statistic){

        this.front = front;
        this.back = back;
        this.statistic = statistic;
    }

    public Card() {

    }
}
