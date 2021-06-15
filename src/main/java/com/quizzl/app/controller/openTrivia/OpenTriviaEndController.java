package com.quizzl.app.controller.openTrivia;

import com.quizzl.app.model.openTrivia.Category;
import com.quizzl.app.util.SpringFxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OpenTriviaEndController {

    @FXML private Label timeLabel;
    @FXML private Label rightAnswerLabel;
    @FXML private Label amountLabel;
    @FXML private Label categoryLabel;
    @FXML private Label difficultyLabel;

    public void setData(long time, int rightAnswers, int amount, Category category, String level){

        long minutes = (time % 3600) / 60;
        long seconds =  time % 60;

        timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
        rightAnswerLabel.setText("Right answers: " + rightAnswers);
        amountLabel.setText("Number of Questions: " + amount);
        categoryLabel.setText("Category: " + category.getName());
        difficultyLabel.setText("Difficulty: " + level);
    }

    public void playAgainAction(ActionEvent actionEvent) throws IOException {

        Button source = (Button) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        FXMLLoader loader = (FXMLLoader) SpringFxmlLoader.getLoader("/view/openTriviaViews/OpenTriviaSettingsView.fxml");
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
