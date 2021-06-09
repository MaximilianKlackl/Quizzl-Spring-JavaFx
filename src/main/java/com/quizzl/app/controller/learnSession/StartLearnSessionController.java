package com.quizzl.app.controller.learnSession;

import com.quizzl.app.controller.manageFlashcards.AddCardController;
import com.quizzl.app.model.Flashcard;
import com.quizzl.app.model.FlashcardStaple;
import com.quizzl.app.repository.FlashcardRepository;
import com.quizzl.app.repository.FlashcardStapleRepository;
import com.quizzl.app.util.SpringFxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class StartLearnSessionController
{
    @FXML private ComboBox<String> stapleListDropdown;
    @FXML private CheckBox repeatQuestion;

    private final FlashcardRepository flashcardRepository;
    private final FlashcardStapleRepository flashcardStapleRepository;

    private FlashcardStaple currentStaple;
    private List<FlashcardStaple> allStaples;

    @Autowired
    public StartLearnSessionController(FlashcardRepository flashcardRepository, FlashcardStapleRepository flashcardStapleRepository)
    {
        this.flashcardRepository = flashcardRepository;
        this.flashcardStapleRepository = flashcardStapleRepository;
    }

    @FXML
    public void initialize()
    {
        allStaples = flashcardStapleRepository.findAll();
        currentStaple = allStaples.get(0);

        allStaples.forEach(staple -> stapleListDropdown.getItems().add(staple.getName()));

        stapleListDropdown.getSelectionModel().selectFirst();
    }

    public void dropDownListener(ActionEvent actionEvent)
    {
        String selectedItem = stapleListDropdown.getSelectionModel().getSelectedItem();
        setCurrentStaple(selectedItem);
    }

    private void setCurrentStaple(String stapleName)
    {
        currentStaple = allStaples
                .stream()
                .filter(staple -> staple.getName().equals(stapleName))
                .findFirst()
                .orElse(null);
    }

    @FXML
    private void startLearnSession(ActionEvent event) throws IOException
    {
        Button source = (Button) event.getSource();

        Stage stage = (Stage) source.getScene().getWindow();
        Parent newScene = null;

        FXMLLoader loader = (FXMLLoader) SpringFxmlLoader.getLoader("/view/learnSessionViews/question.fxml");
        newScene = loader.load();

        Scene scene = new Scene(newScene);
        stage.setScene(scene);
        stage.setTitle("Quizzl");
        stage.show();
    }
}
