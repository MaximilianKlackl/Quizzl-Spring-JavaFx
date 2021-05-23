package com.quizzl.app.controller;

import com.quizzl.app.model.Flashcard;
import com.quizzl.app.model.FlashcardStaple;
import com.quizzl.app.model.Vocab;
import com.quizzl.app.repository.FlashcardRepository;
import com.quizzl.app.repository.FlashcardStapleRepository;
import com.quizzl.app.repository.VocabListRepository;
import com.quizzl.app.repository.VocabRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddCardController {

    @FXML private Label label1;
    @FXML private TextField textField1;
    @FXML private Label label2;
    @FXML private TextField textField2;

    private boolean isFlashcard;
    private Long currentListId;

    private FlashcardStapleRepository flashcardStapleRepository;
    private FlashcardRepository flashcardRepository;
    private VocabRepository vocabRepository;
    private VocabListRepository vocabListRepository;

    @Autowired
    public AddCardController(FlashcardStapleRepository flashcardStapleRepository,
                             FlashcardRepository flashcardRepository,
                             VocabRepository vocabRepository,
                             VocabListRepository vocabListRepository) {
        this.flashcardStapleRepository = flashcardStapleRepository;
        this.flashcardRepository = flashcardRepository;
        this.vocabRepository = vocabRepository;
        this.vocabListRepository = vocabListRepository;
    }

    public void setData(boolean isFlashcard, long currentListId){

        this.currentListId = currentListId;
        this.isFlashcard = isFlashcard;

        if (isFlashcard){
            label1.setText("Question");
            label2.setText("Answer");
        }
        else{
            label1.setText("German");
            label2.setText("English");
        }
    }

    public void cancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Transactional
    public void save(ActionEvent actionEvent) {

        String t1 = textField1.getText();
        String t2 = textField2.getText();

        if(!t1.isEmpty() && !t2.isEmpty()){

            flashcardRepository.create(t1, t2, currentListId);
        }
    }
}
