package com.quizzl.app.controller;

import com.quizzl.app.repository.FlashcardRepository;
import com.quizzl.app.util.CsvUtilities;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class ImportCsvController
{

    private final FlashcardRepository flashcardRepository;

    @Autowired
    public ImportCsvController(FlashcardRepository flashcardRepository)
    {
        this.flashcardRepository = flashcardRepository;
    }

    @FXML
    private TextField filePath;

    @FXML
    private void import_list()
    {
        CsvUtilities.importCards(filePath.getText(), true);
    }
}
