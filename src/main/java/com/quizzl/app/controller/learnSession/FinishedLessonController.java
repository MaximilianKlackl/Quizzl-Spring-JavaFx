package com.quizzl.app.controller.learnSession;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class FinishedLessonController
{
    @FXML private Label statistics;

    public void setDate(int totalQuestions, int rightQuestions, int repeatedQuestions)
    {
        statistics.setText(rightQuestions + " out of " + totalQuestions + " total Questions\n" + repeatedQuestions + " repeatedQuestions");
    }
}
