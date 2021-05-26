package com.quizzl.app.controller.manageFlashcards;

import com.quizzl.app.model.FlashcardStaple;
import com.quizzl.app.service.FlashcardStapleService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddStapleController {

    @FXML private TextField nameField;
    @FXML private TextField descriptionField;
    @FXML private TextField topicFiled;

    private ManageFlashcardsController controller;
    private final FlashcardStapleService service;

    @Autowired
    public AddStapleController(FlashcardStapleService service) {
        this.service = service;
    }

    public void cancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setData(ManageFlashcardsController controller){
        this.controller = controller;
    }

    public void save(ActionEvent actionEvent) {

        String name = nameField.getText();
        String description = descriptionField.getText();
        String topic = topicFiled.getText();

        if(!name.isEmpty() && !description.isEmpty() && !topic.isEmpty()){
            FlashcardStaple staple  = new FlashcardStaple(name, description, topic, null, null);
            service.save(staple);
            controller.updateStaple(staple.getName());
        }

        cancel(actionEvent);
    }
}
