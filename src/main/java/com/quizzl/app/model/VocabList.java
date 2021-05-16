package com.quizzl.app.model.db;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString

@Entity
@DiscriminatorValue("VocabList")
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
