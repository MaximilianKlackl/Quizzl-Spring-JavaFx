package com.quizzl.app.controller.manageFlashcards;

import com.quizzl.app.model.Flashcard;
import com.quizzl.app.model.FlashcardStaple;
import com.quizzl.app.repository.FlashcardRepository;
import com.quizzl.app.repository.FlashcardStapleRepository;
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

@Component
public class ManageFlashcardsController {

    @FXML private Button deleteSelectedButton;
    @FXML private TableView<Flashcard> tableView;
    @FXML private ComboBox<String> stapleListDropdown;

    private final FlashcardStapleRepository flashcardStapleRepository;
    private final FlashcardRepository flashcardRepository;

    private FlashcardStaple currentStaple;
    private List<FlashcardStaple> allStaples;
    private List<Flashcard> selectedFlashcards;

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
        updateTable();

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

        selectedItems.addListener(new ListChangeListener<Flashcard>() {
            @Override
            public void onChanged(Change<? extends Flashcard> change) {
                int size = change.getList().size();
                selectedFlashcards = new ArrayList<>(change.getList());
                deleteSelectedButton.setText("Delete Selected [" + size + "]");
            }
        });
    }

    public void dropDownListener(ActionEvent actionEvent) {

        String selectedItem = stapleListDropdown.getSelectionModel().getSelectedItem();
        setCurrentStaple(selectedItem);
    }

    private void setCurrentStaple(String stapleName){

        currentStaple = allStaples
                .stream()
                .filter(staple -> staple.getName().equals(stapleName))
                .findFirst()
                .orElse(null);

        updateTable();
    }

    public void deleteSelection(ActionEvent actionEvent) {

        currentStaple.getFlashcardList().removeIf(f -> selectedFlashcards.contains(f));

        selectedFlashcards.forEach(s -> flashcardRepository.deleteById(s.getId()));

        updateTable();
    }


    private void updateTable(){
        tableView.setItems(FXCollections.observableList(currentStaple.getFlashcardList()));
    }

    public void createStaple(ActionEvent actionEvent) {

    }

    public void createCard(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = (FXMLLoader) SpringFxmlLoader.getLoader("/view/manageFlashcardsViews/AddCardDialog.fxml");
        Parent parent = loader.load();

        AddCardController controller = loader.getController();
        controller.setData(true, currentStaple.getId());

        Scene scene = new Scene(parent, 400, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void importStaple(ActionEvent actionEvent) {
    }

    public void exportStaple(ActionEvent actionEvent) {
    }
}
