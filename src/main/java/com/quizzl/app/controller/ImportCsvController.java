package com.quizzl.app.controller;

import com.quizzl.app.util.CsvUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ImportCsvController
{

    @FXML
    private Button searchFileButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField filePathTextLabel;

    @FXML
    private Button importButton;

    @FXML
    void handleCancelButtonAction(ActionEvent event) {

    }

    @FXML
    void handleImportFlashcardStapleAction(ActionEvent event) throws IOException {
        importFlashcardStaple();
    }
    @FXML
    private void importFlashcardStaple() throws IOException {
        CsvUtilities.importCards(filePathTextLabel.getText());
    }
}
