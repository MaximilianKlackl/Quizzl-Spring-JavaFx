package com.quizzl.app.controller;

import com.quizzl.app.repository.FlashcardRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SimpleUiController {

    private final FlashcardRepository flashcardRepository;

    @FXML
    public Label label;

    @Autowired
    public SimpleUiController(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    @FXML
    public void initialize () {
        //this.label.setText(String.valueOf(flashcardRepository.getOne(1L).getFront()));
    }
}
