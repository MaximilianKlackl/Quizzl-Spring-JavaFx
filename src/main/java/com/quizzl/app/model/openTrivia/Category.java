package com.quizzl.app.model.openTrivia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor @AllArgsConstructor
@ToString

public class Category implements Serializable {

    private int id;
    private String name;
}
