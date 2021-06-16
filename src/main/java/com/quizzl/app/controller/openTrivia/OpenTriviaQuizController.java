package com.quizzl.app.controller.openTrivia;

import com.quizzl.app.model.openTrivia.Category;
import com.quizzl.app.model.openTrivia.Question;
import com.quizzl.app.service.OpenTriviaService;
import com.quizzl.app.util.SpringFxmlLoader;
import javafx.animation.Animation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static org.springframework.web.util.HtmlUtils.htmlUnescape;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class OpenTriviaQuizController {

    private List<Question> questions;
    private final OpenTriviaService service;
    private boolean isRunning = true;
    private int currentIndexQuestion = 0;
    private int remainingQuestionCount;
    private int rightQuestionCount = 0;
    private int questionAmount;
    private Category category;
    private String level;

    @FXML private GridPane gridPane;
    @FXML private Label timerLabel;
    @FXML private Label questionLabel;
    @FXML private HBox questionHBox;
    @FXML private Label rightQuestionsLabel;
    @FXML private Label remainingLabel;

    @Autowired
    public OpenTriviaQuizController(OpenTriviaService service) {
        this.service = service;
    }

    public void setData(Category category, String difficulty, int amount){
        questions = service.getQuestionsByCategory(category.getId(), amount, difficulty);

        // escape html entity code
        for (Question q: questions) {
            q.setQuestion(htmlUnescape(q.getQuestion()));
        }

        this.questionLabel.setText(questions.get(0).getQuestion());
        this.remainingLabel.setText("Remaining: " + amount);
        this.remainingQuestionCount = amount;
        this.questionAmount = amount;
        this.category = category;
        this.level = difficulty;
    }

    @FXML
    public void initialize() {

        gridPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

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

                        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
                    });
                }
                else
                    timer.cancel();
            }
        }, 0,1000);
    }


    public void falseButtonClick(ActionEvent actionEvent) {
        triggerAnimation(false);
    }

    public void trueButtonClick(ActionEvent actionEvent) {
        triggerAnimation(true);
    }

    private void triggerAnimation(boolean userAnswer){

        boolean rightAnswer =  Boolean.parseBoolean(questions.get(currentIndexQuestion).getCorrectAnswer());

        if(rightAnswer == userAnswer){
            rightQuestionCount++;
            rightQuestionsLabel.setText("Right: " + rightQuestionCount);
        }

        remainingQuestionCount--;
        remainingLabel.setText("Remaining: " + remainingQuestionCount);

        Color color = userAnswer == rightAnswer ? Color.GREEN : Color.RED;

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), evt -> questionHBox.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY,Insets.EMPTY)))),
                new KeyFrame(Duration.seconds(1), evt -> questionHBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,Insets.EMPTY)))),
                new KeyFrame(Duration.seconds(1), evt -> nextQuestion()));

        timeline.setCycleCount(1);
        timeline.play();
    }

    private void nextQuestion() {
        currentIndexQuestion++;
        if(questions.size() != currentIndexQuestion){

            questionLabel.setText(questions.get(currentIndexQuestion).getQuestion());
        }
        else{
            handleEnd();
        }
    }

    private void handleEnd() {

        isRunning = false;

        Stage stage = (Stage) gridPane.getScene().getWindow();

        FXMLLoader loader = (FXMLLoader) SpringFxmlLoader.getLoader("/view/openTriviaViews/OpenTriviaEndView.fxml");
        Parent parent = null;

        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        OpenTriviaEndController controller = loader.getController();
        controller.setData(parseTime(), rightQuestionCount, questionAmount, category, level);

        assert parent != null;
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    private long parseTime(){
        String timeString = timerLabel.getText();
        return Long.parseLong(timeString.split(":")[0]) * 60+ Long.parseLong(timeString.split(":")[1]);
    }
}
