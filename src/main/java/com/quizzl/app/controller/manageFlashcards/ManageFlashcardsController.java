package com.quizzl.app.controller.manageFlashcards;

import com.quizzl.app.model.Flashcard;
import com.quizzl.app.model.FlashcardStaple;
import com.quizzl.app.service.FlashcardService;
import com.quizzl.app.service.FlashcardStapleService;
import com.quizzl.app.util.SpringFxmlLoader;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO: REFACTOR UPDATING DB AND UI METHODS

@Component
public class ManageFlashcardsController {

    @FXML private Button deleteSelectedButton;
    @FXML private TableView<Flashcard> tableView;
    @FXML private ComboBox<String> stapleListDropdown;

    private final FlashcardStapleService stapleService;
    private final FlashcardService flashcardService;

    private FlashcardStaple currentStaple;
    private List<FlashcardStaple> allStaples;
    private List<Flashcard> selectedFlashcards;

    @Autowired
    public ManageFlashcardsController(FlashcardStapleService stapleService, FlashcardService flashcardService) {
        this.stapleService = stapleService;
        this.flashcardService = flashcardService;
    }


    @FXML
    public void initialize() {

        // set default
        allStaples = stapleService.findAll();
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

        // detect selected rows
        ObservableList<Flashcard> selectedItems = tableView.getSelectionModel().getSelectedItems();

        selectedItems.addListener((ListChangeListener<Flashcard>) change -> {
            int size = change.getList().size();
            selectedFlashcards = new ArrayList<>(change.getList());
            deleteSelectedButton.setText("Delete Selected [" + size + "]");
        });
    }

    public void dropDownListener(ActionEvent actionEvent) {

        String selectedItem = stapleListDropdown.getSelectionModel().getSelectedItem();

        currentStaple = stapleService.findAll()
                .stream()
                .filter(staple -> staple.getName().equals(selectedItem))
                .findFirst()
                .orElse(currentStaple);

        tableView.setItems(FXCollections.observableList(currentStaple.getFlashcardList()));
    }

    public void deleteSelection(ActionEvent actionEvent) {

        currentStaple.getFlashcardList().removeIf(f -> selectedFlashcards.contains(f));
        selectedFlashcards.forEach(s -> flashcardService.deleteById(s.getId()));
        tableView.setItems(FXCollections.observableList(currentStaple.getFlashcardList()));
    }


    public void createStaple(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = (FXMLLoader) SpringFxmlLoader.getLoader("/view/manageFlashcardsViews/AddStapleDialog.fxml");
        Parent parent = loader.load();

        AddStapleController controller = loader.getController();
        controller.setData(this);

        Scene scene = new Scene(parent, 400, 250);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void createCard(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = (FXMLLoader) SpringFxmlLoader.getLoader("/view/manageFlashcardsViews/AddCardDialog.fxml");
        Parent parent = loader.load();

        AddCardController controller = loader.getController();
        controller.setData(currentStaple, this);

        Scene scene = new Scene(parent, 400, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void updateStaple(String name){

        allStaples = stapleService.findAll();
        currentStaple = stapleService.findOne(currentStaple.getId());

        stapleListDropdown.setItems(FXCollections.observableList(allStaples.stream()
                .map(FlashcardStaple::getName)
                .collect(Collectors.toList())));

        stapleListDropdown.getSelectionModel().select(name);

        tableView.setItems(FXCollections.observableList(currentStaple.getFlashcardList()));
    }

    public void updateAll(){
        allStaples = stapleService.findAll();
        currentStaple = stapleService.findOne(currentStaple.getId());

        tableView.setItems(FXCollections.observableList(currentStaple.getFlashcardList()));
    }

    public void importStaple(ActionEvent actionEvent) {

    }

    public void exportStaple(ActionEvent actionEvent) {

    }
}
