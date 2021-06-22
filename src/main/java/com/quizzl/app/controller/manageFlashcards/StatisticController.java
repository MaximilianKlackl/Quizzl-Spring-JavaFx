package com.quizzl.app.controller.manageFlashcards;

import com.quizzl.app.model.dbEntities.FlashcardStaple;
import com.quizzl.app.model.dbEntities.Statistic;
import com.quizzl.app.service.StatisticService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticController {

    @FXML private Label stapleNameLabel;
    @FXML private Label timeSpendLabel;
    @FXML private Label learnProgressLabel;

    public void setData(FlashcardStaple flashcardStaple){

        if (flashcardStaple.getStatistic() != null){
            Statistic statistic = flashcardStaple.getStatistic();
            stapleNameLabel.setText(flashcardStaple.getName());
            timeSpendLabel.setText("Time spend: " + statistic.getTimeSpend());
            learnProgressLabel.setText("Learn Progress: " + statistic.getLearnProgress() * 100 + "%");
        }else{
            stapleNameLabel.setText("No statistic available");
            timeSpendLabel.setText("");
            learnProgressLabel.setText("");
        }
    }

    public void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
