package com.quizzl.app.controller;

import com.quizzl.app.util.SpringFxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NavBarController {

    @FXML
    private void handleMenuButtonAction (ActionEvent event) throws IOException {

        Button source = (Button) event.getSource();
        String navigateTo = source.getText();
        Stage stage = (Stage) source.getScene().getWindow();
        Parent newScene = null;

        switch (navigateTo){

            case "Manage Flashcards": {
                newScene = (Parent) SpringFxmlLoader.load("/view/manageFlashcardsViews/ManageFlashcardsView.fxml");
                break;
            }
            case "Manage Vocabs": {
                newScene = (Parent) SpringFxmlLoader.load("/view/manageVocabsViews/ManageVocabView.fxml");
                break;
            }
            case "Start Learn Session": {
                newScene = (Parent) SpringFxmlLoader.load("/view/learnSessionViews/learnSessionView.fxml");
                break;
            }
            case "Open Trivia": {
                newScene = (Parent) SpringFxmlLoader.load("/view/openTriviaViews/openTriviaView.fxml");
                break;
            }
        }

        if(newScene != null){
            Scene scene = new Scene(newScene);
            stage.setScene(scene);
            stage.setTitle("Quizzl");
            stage.show();
        }
    }
}
