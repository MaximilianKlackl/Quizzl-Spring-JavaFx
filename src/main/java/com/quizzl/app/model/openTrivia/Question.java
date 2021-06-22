package com.quizzl.app.model.openTrivia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@NoArgsConstructor @AllArgsConstructor
@ToString
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {

    private String category;
    private String question;

    @JsonProperty("correct_answer")
    private String correctAnswer;

    public boolean correctAnswer(){
        return correctAnswer.equals("True");
    }
}
