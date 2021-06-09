package com.quizzl.app.controller;

import com.quizzl.app.repository.FlashcardRepository;
import com.quizzl.app.util.CsvUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ExportCsvController
{
    private final FlashcardRepository flashcardRepository;


    @Autowired
    public ExportCsvController(FlashcardRepository flashcardRepository)
    {
        this.flashcardRepository = flashcardRepository;
    }

    @FXML
    private Button searchFileButton;

    @FXML
    private Button exportButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField filePathTextLabel;

    @FXML
    private ComboBox<?> flashStapleComboBox;

    @FXML
    private void export_list() throws IOException {
        CsvUtilities.exportCards(null, filePathTextLabel.getText());
    }

    @FXML
    public void handleCloseButtonAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
