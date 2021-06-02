package com.quizzl.app.controller;

import com.quizzl.app.repository.FlashcardRepository;
import com.quizzl.app.util.CsvUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private TextField filePath;

    @FXML
    private void export_list()
    {
        //CsvUtilities.exportCards(null, filePath.getText());
    }

    public void exportStaple(ActionEvent actionEvent) {
    }

    public void dropDownListener(ActionEvent actionEvent) {
    }
}
