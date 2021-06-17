package com.quizzl.app.controller.manageFlashcards;

import com.quizzl.app.model.dbEntities.FlashcardStaple;
import com.quizzl.app.service.FlashcardStapleService;
import com.quizzl.app.util.CsvUtilities;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class ExportCsvController
{
    @FXML private ComboBox<String> stapleComboBox;
    @FXML private TextField filePathField;

    private final FlashcardStapleService service;

    @Autowired
    public ExportCsvController(FlashcardStapleService service) {
        this.service = service;
    }

    public void setData(FlashcardStaple current){

        stapleComboBox.setItems(FXCollections.observableList(service.findAll().stream()
                .map(FlashcardStaple::getName)
                .collect(Collectors.toList())));

        stapleComboBox.getSelectionModel().select(current.getName());
    }

    @FXML
    private void export() throws IOException {
        FlashcardStaple selected = service.findAll().stream()
                .filter(s -> s.getName().equals(stapleComboBox.getSelectionModel().getSelectedItem()))
                .findFirst()
                .orElse(null);

        assert selected != null;
        CsvUtilities.exportCards(selected, filePathField.getText());

        Stage stage  = (Stage) filePathField.getScene().getWindow();
        stage.close();
    }

    public void openFileBrowser(ActionEvent actionEvent) {

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Export Flashcard");
        File defaultDirectory = new File("C:\\Users\\User");
        chooser.setInitialDirectory(defaultDirectory);

        Stage stage = new Stage();
        File selectedDirectory = chooser.showDialog(stage);
        filePathField.setText(selectedDirectory.getAbsolutePath());
    }

    public void cancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
