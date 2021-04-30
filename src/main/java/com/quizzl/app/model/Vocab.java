package com.quizzl.app.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
@Setter

@Entity
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
