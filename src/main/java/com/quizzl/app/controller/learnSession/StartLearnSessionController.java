package com.quizzl.app.controller.learnSession;

import com.quizzl.app.model.Flashcard;
import com.quizzl.app.model.FlashcardStaple;
import com.quizzl.app.repository.FlashcardRepository;
import com.quizzl.app.repository.FlashcardStapleRepository;
import com.quizzl.app.util.SpringFxmlLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class StartLearnSessionController
{
    @FXML private ComboBox<String> stapleListDropdown;
    @FXML private CheckBox repeatQuestion;
    @FXML private Slider slider;
    @FXML private Label amount;

    private final FlashcardRepository flashcardRepository;
    private final FlashcardStapleRepository flashcardStapleRepository;

    private FlashcardStaple currentStaple;
    private List<FlashcardStaple> allStaples;

    @Autowired
    public StartLearnSessionController(FlashcardRepository flashcardRepository, FlashcardStapleRepository flashcardStapleRepository)
    {
        this.flashcardRepository = flashcardRepository;
        this.flashcardStapleRepository = flashcardStapleRepository;
    }

    @FXML
    public void initialize()
    {
        allStaples = flashcardStapleRepository.findAll();
        currentStaple = allStaples.get(0);

        allStaples.forEach(staple -> stapleListDropdown.getItems().add(staple.getName()));

        stapleListDropdown.getSelectionModel().selectFirst();

        slider.setMin(1);
        slider.setMax(currentStaple.getFlashcardList().size());
        slider.setValue(1);

        amount.setText(Double.toString(slider.getValue()));

        slider.valueProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                amount.setText(Integer.toString(new_val.intValue()));
            }
        });
    }

    public void dropDownListener()
    {
        String selectedItem = stapleListDropdown.getSelectionModel().getSelectedItem();
        setCurrentStaple(selectedItem);

        slider.setMax(currentStaple.getFlashcardList().size());
        slider.setValue(1);
    }

    private void setCurrentStaple(String stapleName)
    {
        currentStaple = allStaples
                .stream()
                .filter(staple -> staple.getName().equals(stapleName))
                .findFirst()
                .orElse(null);
    }

    @FXML
    private void startLearnSession(ActionEvent event) throws IOException
    {
        if (currentStaple.getFlashcardList().size() > 0)
        {
            Button source = (Button) event.getSource();

            Stage stage = (Stage) source.getScene().getWindow();
            Parent newScene = null;

            FXMLLoader loader = (FXMLLoader) SpringFxmlLoader.getLoader("/view/learnSessionViews/question.fxml");
            newScene = loader.load();

            QuestionController controller = loader.getController();
            controller.setData(currentStaple.getFlashcardList(), repeatQuestion.isSelected(), (int) slider.getValue());


            Scene scene = new Scene(newScene);
            stage.setScene(scene);
            stage.setTitle("Quizzl");
            stage.show();
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        FXMLLoader loader = (FXMLLoader) SpringFxmlLoader.getLoader("/view/manageFlashcardsViews/ManageFlashcardsView.fxml");
        Parent newScene = loader.load();

        Scene scene = new Scene(newScene, 1280, 720);
        stage.setScene(scene);
        stage.setTitle("Quizzl");
        stage.show();
    }
}
