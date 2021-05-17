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
@DiscriminatorValue("Vocab")
public class Vocab extends Card {

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "cardlist_id")
    private VocabList vocabList;

    public Vocab(String back, String front){
        super(front, back);
    }

    public Vocab(){

    }
}
