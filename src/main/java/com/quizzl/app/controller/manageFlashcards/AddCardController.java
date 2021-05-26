package com.quizzl.app.controller.manageFlashcards;

import com.quizzl.app.model.Flashcard;
import com.quizzl.app.model.FlashcardStaple;
import com.quizzl.app.service.FlashcardService;
import com.quizzl.app.util.SpringFxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddCardController {

    @FXML private TextField questionField;
    @FXML private TextField answerField;

    private FlashcardStaple currentStaple;
    private final FlashcardService service;

    private ManageFlashcardsController manageFlashcardsController;

    @Autowired
    public AddCardController(FlashcardService service) {
        this.service = service;
    }

    public void setData(FlashcardStaple currentStaple, ManageFlashcardsController manageFlashcardsController){

        this.currentStaple = currentStaple;
        this.manageFlashcardsController = manageFlashcardsController;
    }

    public void cancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void save(ActionEvent actionEvent) {

        String question = questionField.getText();
        String answer = answerField.getText();

        if(!question.isEmpty() && !answer.isEmpty()){

            Flashcard flashcard = new Flashcard(question, answer, null, currentStaple);
            service.save(flashcard);
            manageFlashcardsController.updateAll();
        }

        cancel(actionEvent);
    }
}
