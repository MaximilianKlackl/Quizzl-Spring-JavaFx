package com.quizzl.app.controller;

import com.quizzl.app.model.FlashcardStaple;
import com.quizzl.app.repository.FlashcardRepository;
import com.quizzl.app.repository.FlashcardStapleRepository;
import com.quizzl.app.util.CsvUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class ImportCsvController
{
    @FXML private TextField filePath;

    private final FlashcardRepository flashcardRepository;
    private final FlashcardStapleRepository flashcardStapleRepository;


    @Autowired
    public ImportCsvController(FlashcardRepository flashcardRepository, FlashcardStapleRepository flashcardStapleRepository)
    {
        this.flashcardRepository = flashcardRepository;
        this.flashcardStapleRepository = flashcardStapleRepository;
    }

    @FXML
    private void importStaple()
    {
        flashcardStapleRepository.save(CsvUtilities.importCards(filePath.getText()));
    }
}
