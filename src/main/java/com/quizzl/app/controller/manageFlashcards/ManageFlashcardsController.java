package com.quizzl.app.controller.manageFlashcards;

import com.quizzl.app.model.Flashcard;
import com.quizzl.app.model.FlashcardStaple;
import com.quizzl.app.repository.FlashcardStapleRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManageFlashcardsController {

    @FXML private TableView<FlashcardTableModel> tableView;
    @FXML private TableColumn<FlashcardTableModel, Boolean> selectColumn;
    @FXML private TableColumn<FlashcardTableModel, Long> idColumn;
    @FXML private TableColumn<FlashcardTableModel, String> questionColumn;
    @FXML private TableColumn<FlashcardTableModel, String> answerColumn;

    @FXML public ComboBox stapleListDropdown;

    private final FlashcardStapleRepository flashcardStapleRepository;
    private final ObservableList<FlashcardTableModel> data = FXCollections.observableArrayList();


    @Autowired
    public ManageFlashcardsController(FlashcardStapleRepository flashcardStapleRepository){
        this.flashcardStapleRepository = flashcardStapleRepository;
    }

    @FXML
    public void initialize() {

        // fetch data from db
        List<FlashcardStaple> flashcardStapleList = flashcardStapleRepository.findAll();
        for (FlashcardStaple staple: flashcardStapleList) {
            for (Flashcard card: staple.getFlashcardList()) {
                data.add(new FlashcardTableModel(false, card.getId(), card.getFront(), card.getBack()));
            }
        }

        System.out.println(tableView);
        tableView.setItems(data);

        // responsive column size
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.getColumns().get(0).prefWidthProperty().bind(tableView.widthProperty().multiply(0.5));
        tableView.getColumns().get(1).prefWidthProperty().bind(tableView.widthProperty().multiply(0.5));
        tableView.getColumns().get(2).prefWidthProperty().bind(tableView.widthProperty().multiply(0.40));
        tableView.getColumns().get(3).prefWidthProperty().bind(tableView.widthProperty().multiply(0.40));
    }
}
