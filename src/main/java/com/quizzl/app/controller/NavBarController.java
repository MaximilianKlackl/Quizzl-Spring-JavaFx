package com.quizzl.app.controller;

import com.quizzl.app.util.SpringFxmlLoader;
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

    @FXML
    private void handleMenuButtonAction (ActionEvent event) throws IOException {

        Button source = (Button) event.getSource();
        String navigateTo = source.getText();
        Stage stage = (Stage) source.getScene().getWindow();
        Parent newScene = null;

        switch (navigateTo){

            case "Manage Flashcards": {
                FXMLLoader loader = (FXMLLoader) SpringFxmlLoader.getLoader("/view/manageFlashcardsViews/ManageFlashcardsView.fxml");
                newScene = loader.load();
                break;
            }
            case "Start Learn Session": {

                FXMLLoader loader = (FXMLLoader) SpringFxmlLoader.getLoader("/view/learnSessionViews/learnSessionView.fxml");
                newScene = loader.load();
                break;
            }
            case "Open Trivia": {
                FXMLLoader loader = (FXMLLoader) SpringFxmlLoader.getLoader("/view/openTriviaViews/OpenTriviaSettingsView.fxml");
                newScene = loader.load();
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
