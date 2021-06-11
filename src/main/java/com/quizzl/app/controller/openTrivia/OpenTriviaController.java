package com.quizzl.app.controller.openTrivia;

import com.quizzl.app.model.openTrivia.Category;
import com.quizzl.app.service.OpenTriviaService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OpenTriviaController {

    @FXML private Label questionSliderLabel;
    @FXML private Slider questionSlider;
    @FXML private ComboBox difficultyComboBox;
    @FXML private ComboBox categoryComboBox;

    private final OpenTriviaService service;

    private Category category;

    @Autowired
    public OpenTriviaController(OpenTriviaService service) {
        this.service = service;
    }

    @FXML
    public void initialize() {

        // set defaults
        // TODO: Throw no internet connection error:
        categoryComboBox.setItems(FXCollections.observableList(
                service.getAllCategories().stream()
                        .map(Category::getName)
                        .collect(Collectors.toList())));

        categoryComboBox.getSelectionModel().selectFirst();

        // maybe create Enum
        difficultyComboBox.setItems(FXCollections.observableArrayList("any", "easy", "medium", "hard"));
        difficultyComboBox.getSelectionModel().selectFirst();

        // set slider listener

        questionSlider.valueProperty().addListener((observable, oldValue, newValue) -> {

            questionSliderLabel.setText("Questions [" + newValue.intValue() + "]");
        });
    }

    public void startAction(ActionEvent actionEvent) {

    }
}
