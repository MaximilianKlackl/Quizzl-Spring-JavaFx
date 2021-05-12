package com.quizzl.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NavBarController {

    @FXML private Button learnSessionButton;
    @FXML private Button manageFlashcardsButton;
    @FXML private Button manageVocabsButton;
    @FXML private Button openTriviaButton;

    @FXML
    private void handleMenuButtonAction (ActionEvent event) throws IOException {

        Button source = (Button) event.getSource();
        String navigateTo = source.getText();
        Stage stage = (Stage) source.getScene().getWindow();
        Parent newScene = null;

        switch (navigateTo){

            case "Manage Flashcards": {
                newScene = FXMLLoader.load(getClass().getResource("/view/manageFlashcardsViews/ManageFlashcardsView.fxml"));
                break;
            }
            case "Manage Vocabs": {
                newScene = FXMLLoader.load(getClass().getResource("/view/manageVocabsViews/ManageVocabView.fxml"));
                break;
            }
            case "Start Learn Session": {
                newScene = FXMLLoader.load(getClass().getResource("/view/learnSessionViews/learnSessionView.fxml"));
                break;
            }
            case "Open Trivia": {
                newScene = FXMLLoader.load(getClass().getResource("/view/openTriviaViews/openTriviaView.fxml"));
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
