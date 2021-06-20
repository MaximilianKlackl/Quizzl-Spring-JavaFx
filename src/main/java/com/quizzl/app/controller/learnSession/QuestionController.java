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
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Component
public class QuestionController
{
    private int questionAmount;
    private int rightQuestions;
    private int current;
    private boolean isQuestion;
    private boolean repeated;

    @FXML private Label statistic;
    @FXML private Label questionAnswer;

    private List<Flashcard> flashcardStaple;
    private List<Flashcard> wrongCards;

    public void setData(List<Flashcard> flashcardStaple, int questionAmount)
    {
        this.flashcardStaple = new LinkedList<>(flashcardStaple);
        this.current = 0;
        this.questionAmount = this.flashcardStaple.size();
        this.statistic.setText(questionAmount + "/" + 0);
        this.questionAnswer.setText(flashcardStaple.get(current).getQuestion());
    }

    @FXML private void initialize()
    {
        this.rightQuestions = 0;
        this.isQuestion = true;
        this.statistic.setText(questionAmount + "/" + rightQuestions);
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

    @FXML private void setAnswer(ActionEvent event) throws IOException {
        Button source  = (Button)event.getSource();

        if (source.getText().equals("Right"))
        {
            rightQuestions++;
        }

        statistic.setText(questionAmount + "/" + rightQuestions);

        current++;

        if (current == questionAmount)
        {
            System.out.println("Correct Questions: " + rightQuestions + "\nWrong Questions: " + (questionAmount - rightQuestions));

            // get new FXMLLoader
            FXMLLoader loader = (FXMLLoader) SpringFxmlLoader.getLoader("/view/learnSessionViews/finishedSession.fxml");
            Parent parent = loader.load();

            FinishedLessonController controller = loader.getController();
            controller.setDate(questionAmount, rightQuestions);

            Scene scene = new Scene(parent, 720, 400);
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
        else
        {
            questionAnswer.setText(flashcardStaple.get(current).getQuestion());
        }
    }

    @FXML void skip()
    {
        if (current < questionAmount)
        {
            current++;
            questionAnswer.setText(flashcardStaple.get(current).getQuestion());
        }
    }
}
