package com.quizzl.app.controller.learnSession;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class FinishedLessonController
{
    @FXML private Label statistics;
    @FXML private Label time;

    public void setDate(int totalQuestions, int rightQuestions, String time)
    {
        this.statistics.setText(rightQuestions + " out of " + totalQuestions + " total Questions\n");
        this.time.setText("Time: " + time);
    }
}
