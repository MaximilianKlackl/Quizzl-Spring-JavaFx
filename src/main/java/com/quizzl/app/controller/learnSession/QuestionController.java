package com.quizzl.app.controller.learnSession;

import com.quizzl.app.model.Flashcard;
import com.quizzl.app.util.SpringFxmlLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class QuestionController
{
    private int questionAmount;
    private int totalQuestionAmount;
    private int rightQuestions;
    private int repeatedQuestions;
    private int current;
    private boolean isQuestion;

    private boolean repeated;

    @FXML private Label statistic;
    @FXML private Label timeStatistic;
    @FXML private Label questionAnswer;

    private List<Flashcard> flashcardStaple;
    private List<Flashcard> wrongCards;

    public void setData(List<Flashcard> flashcardStaple, boolean repeated, int questionAmount)
    {
        this.flashcardStaple = new LinkedList<>(flashcardStaple);
        this.current = 0;
        this.questionAmount = questionAmount;
        this.statistic.setText(this.questionAmount + "/" + 0);
        this.questionAnswer.setText(flashcardStaple.get(current).getQuestion());
        this.repeated = repeated;
    }

    @FXML private void initialize()
    {
        this.rightQuestions = 0;
        this.repeatedQuestions = 0;
        this.isQuestion = true;
        this.statistic.setText(questionAmount + "/" + rightQuestions);

        this.wrongCards = new LinkedList<>();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(isRunning)
                {
                    Platform.runLater(() -> {

                        long seconds = parseTime();
                        seconds++;
                        long minutes = (seconds % 3600) / 60;
                        seconds = seconds % 60;

                        timeStatistic.setText(String.format("%02d:%02d", minutes, seconds));
                    });
                }
                else
                    timer.cancel();
            }
        }, 0,1000);
    }

    @FXML private void stop(ActionEvent event) throws IOException
    {
        end(event);
    }

    @FXML private void turn()
    {
        if (isQuestion)
        {
            questionAnswer.setText(flashcardStaple.get(current).getAnswer());
        }
        else {
            questionAnswer.setText(flashcardStaple.get(current).getQuestion());
        }
        this.isQuestion = !isQuestion;
    }

    @FXML private void setAnswer(ActionEvent event) throws IOException
    {
        Button source  = (Button)event.getSource();

        if (source.getText().equals("Right"))
        {
            rightQuestions++;
        }
        else if (repeated)
        {
            wrongCards.add(flashcardStaple.get(current));
        }


        statistic.setText(questionAmount + "/" + rightQuestions);

        current++;

        if (current == questionAmount)
        {
            end(event);
        }
        else
        {
            questionAnswer.setText(flashcardStaple.get(current).getQuestion());
        }
    }

    @FXML void skip(ActionEvent event) throws IOException
    {
        current++;
        questionAnswer.setText(flashcardStaple.get(current).getQuestion());

        if (current == questionAmount)
        {
            end(event);
        }
        else
        {
            questionAnswer.setText(flashcardStaple.get(current).getQuestion());
        }
    }

    private void end(ActionEvent event) throws IOException
    {
        //Checking to see if all cards have been iterated through


            Button source = (Button) event.getSource();

            // get new FXMLLoader
            FXMLLoader loader = (FXMLLoader) SpringFxmlLoader.getLoader("/view/learnSessionViews/finishedSession.fxml");
            Parent parent = loader.load();

            FinishedLessonController controller = loader.getController();
            controller.setDate(questionAmount, rightQuestions, repeatedQuestions);

            Scene scene = new Scene(parent, 600, 400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();

            //Background Change
            stage = (Stage) source.getScene().getWindow();

            loader = (FXMLLoader) SpringFxmlLoader.getLoader("/view/learnSessionViews/startLearnSessionView.fxml");
            parent = loader.load();

            scene = new Scene(parent, 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Quizzl");
            stage.show();
    }
}
