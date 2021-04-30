package com.quizzl.app.model;

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

    public CardList() {

    }
}
