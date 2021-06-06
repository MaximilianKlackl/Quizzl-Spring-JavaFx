package com.quizzl.app.controller.openTrivia;

import com.quizzl.app.model.openTrivia.Category;
import com.quizzl.app.service.OpenTriviaService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OpenTriviaController {

    @FXML private Slider questionSlider;
    @FXML private ComboBox difficultyComboBox;
    @FXML private ComboBox categoryComboBox;

    private final OpenTriviaService service;

    private Category category;
    private int numberOfQuestions;
    // should be a enum, but is not easy to implemented when used in Question Class to parse it from Json...
    private String difficulty;

    @Autowired
    public OpenTriviaController(OpenTriviaService service) {
        this.service = service;
    }

    @FXML
    public void initialize() {
        numberOfQuestions = 10;
        difficulty = "any";

        // set defaults
        //categoryComboBox.setItems(FXCollections.observableList(service.getAllCategories()));
        difficultyComboBox.setItems(FXCollections.observableArrayList("any", "easy", "medium", "hard"));

    }

    public void startAction(ActionEvent actionEvent) {

    }
}
