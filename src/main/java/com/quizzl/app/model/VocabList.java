package com.quizzl.app.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString

@Entity
public class VocabList extends CardList {

    private String unit;

    @OneToMany(
            mappedBy = "vocabList",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    private List<Vocab> vocabList;

    public VocabList(String unit, String name, String description){
        super(name, description);
        this.unit = unit;
    }

    public VocabList() {

    }
}
