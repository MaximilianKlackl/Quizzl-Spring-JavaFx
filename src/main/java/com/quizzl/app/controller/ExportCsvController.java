package com.quizzl.app.controller;

import com.quizzl.app.model.Flashcard;
import com.quizzl.app.model.FlashcardStaple;
import com.quizzl.app.repository.FlashcardRepository;
import com.quizzl.app.repository.FlashcardStapleRepository;
import com.quizzl.app.util.CsvUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExportCsvController
{
    @FXML private ComboBox<String> stapleListDropdown;
    @FXML private TextField filePath;

    private final FlashcardRepository flashcardRepository;
    private final FlashcardStapleRepository flashcardStapleRepository;

    private FlashcardStaple currentStaple;
    private List<FlashcardStaple> allStaples;


    @Autowired
    public ExportCsvController(FlashcardRepository flashcardRepository, FlashcardStapleRepository flashcardStapleRepository)
    {
        this.flashcardRepository = flashcardRepository;
        this.flashcardStapleRepository = flashcardStapleRepository;
    }

    @FXML
    public void initialize()
    {
        allStaples = flashcardStapleRepository.findAll();
        currentStaple = allStaples.get(0);

        allStaples.forEach(staple -> stapleListDropdown.getItems().add(staple.getName()));

        stapleListDropdown.getSelectionModel().selectFirst();
    }

    public void dropDownListener(ActionEvent actionEvent)
    {

        String selectedItem = stapleListDropdown.getSelectionModel().getSelectedItem();
        setCurrentStaple(selectedItem);
    }

    private void setCurrentStaple(String stapleName)
    {
        currentStaple = allStaples
                .stream()
                .filter(staple -> staple.getName().equals(stapleName))
                .findFirst()
                .orElse(null);
    }

    @FXML
    private void exportStaple()
    {
        CsvUtilities.exportCards(currentStaple, filePath.getText());
    }
}
