package com.quizzl.app.model.openTrivia;

import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor @AllArgsConstructor
@ToString

public class Category implements Serializable {

    private int id;
    private String name;
}
