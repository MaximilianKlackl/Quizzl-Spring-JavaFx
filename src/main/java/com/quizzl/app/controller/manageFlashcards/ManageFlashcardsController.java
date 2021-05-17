package com.quizzl.app.controller.manageFlashcards;

import com.quizzl.app.model.Flashcard;
import com.quizzl.app.model.FlashcardStaple;
import com.quizzl.app.repository.FlashcardRepository;
import com.quizzl.app.repository.FlashcardStapleRepository;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManageFlashcardsController {

    @FXML private TableView<Flashcard> tableView;
    @FXML public ComboBox<String> stapleListDropdown;

    private final FlashcardStapleRepository flashcardStapleRepository;
    private final FlashcardRepository flashcardRepository;

    private FlashcardStaple currentStaple;
    private List<FlashcardStaple> allStaples;

    @Autowired
    public ManageFlashcardsController(FlashcardStapleRepository flashcardStapleRepository, FlashcardRepository flashcardRepository){
        this.flashcardStapleRepository = flashcardStapleRepository;
        this.flashcardRepository = flashcardRepository;
    }

    @FXML
    public void initialize() {

        // set default
        allStaples = flashcardStapleRepository.findAll();
        currentStaple = allStaples.get(0);
        tableView.setItems(FXCollections.observableList(currentStaple.getFlashcardList()));

        // add staple names
        allStaples.forEach(staple -> stapleListDropdown.getItems().add(staple.getName()));

        // set responsive column size
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.getColumns().get(0).prefWidthProperty().bind(tableView.widthProperty().multiply(0.10));
        tableView.getColumns().get(1).prefWidthProperty().bind(tableView.widthProperty().multiply(0.45));
        tableView.getColumns().get(2).prefWidthProperty().bind(tableView.widthProperty().multiply(0.45));

        // staple dropdown default value
        stapleListDropdown.getSelectionModel().selectFirst();
    }

    public void dropDownListener(ActionEvent actionEvent) {
        // get selected staple
        String selectedItem = stapleListDropdown.getSelectionModel().getSelectedItem();
        // change table data
        setCurrentStaple(selectedItem);
    }

    public void setCurrentStaple(String stapleName){

        currentStaple = allStaples
                .stream()
                .filter(staple -> staple.getName().equals(stapleName))
                .findFirst()
                .orElse(allStaples.get(0));

        tableView.setItems(FXCollections.observableList(currentStaple.getFlashcardList()));
    }
}
