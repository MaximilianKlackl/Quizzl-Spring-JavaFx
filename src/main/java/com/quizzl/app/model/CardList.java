package com.quizzl.app.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@ToString
@Setter
@Getter
@Entity
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype",
        discriminatorType = DiscriminatorType.STRING)
public class CardList extends BaseEntity{

    private String name;
    private String description;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "statistic_id")
    private Statistic statistic;

    public CardList(String name, String description){
        this.name = name;
        this.description = description;
    }

    public CardList(String name, String description ,Statistic statistic){
        this.name = name;
        this.statistic = statistic;
        this.description = description;
    }

    public CardList() {

    }
}
