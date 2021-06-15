package com.quizzl.app.controller.openTrivia;

import com.quizzl.app.controller.manageFlashcards.AddCardController;
import com.quizzl.app.model.openTrivia.Category;
import com.quizzl.app.service.OpenTriviaService;
import com.quizzl.app.util.SpringFxmlLoader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OpenTriviaSettingsController {

    @FXML private Label questionSliderLabel;
    @FXML private Slider questionSlider;
    @FXML private ComboBox<String> difficultyComboBox;
    @FXML private ComboBox<String> categoryComboBox;

    private final OpenTriviaService service;
    private List<Category> allCategories;

    @Autowired
    public OpenTriviaSettingsController(OpenTriviaService service) {
        this.service = service;
    }

    @FXML
    public void initialize() {

        // set defaults
        // TODO: Throw no internet connection error:
        allCategories = service.getAllCategories();

        categoryComboBox.setItems(FXCollections.observableList(
                allCategories.stream()
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

    public void startAction(ActionEvent actionEvent) throws IOException {

        Button source = (Button) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        FXMLLoader loader = (FXMLLoader) SpringFxmlLoader.getLoader("/view/openTriviaViews/OpenTriviaQuizView.fxml");
        Parent parent = loader.load();

        OpenTriviaQuizController controller = loader.getController();
        controller.setData(getCategory(), getDifficulty(), getAmount());

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    private int getAmount(){
        return (int) questionSlider.getValue();
    }

    private String getDifficulty(){
        return (String) difficultyComboBox.getSelectionModel().getSelectedItem();
    }

    private Category getCategory(){
        return allCategories.stream()
                .filter(c -> c.getName().equals(categoryComboBox.getSelectionModel().getSelectedItem()))
                .findFirst()
                .orElse(allCategories.get(0));
    }
}
