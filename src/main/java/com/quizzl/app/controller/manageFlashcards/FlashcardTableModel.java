package com.quizzl.app.controller.manageFlashcards;

import javafx.beans.property.*;

public class FlashcardTableModel{

    // doesnt work without initializing here
    // fails if init is in constructor
    private final SimpleBooleanProperty isSelected = new SimpleBooleanProperty(false);
    private final SimpleLongProperty id = new SimpleLongProperty(1L);
    private final SimpleStringProperty question = new SimpleStringProperty("");
    private final SimpleStringProperty answer = new SimpleStringProperty("");

    public FlashcardTableModel(boolean isSelected, Long id, String question, String answer){
        this.isSelected.set(isSelected);
        this.id.set(id);
        this.question.set(question);
        this.answer.set(answer);
    }

    public boolean isIsSelected() {
        return isSelected.get();
    }

    public SimpleBooleanProperty isSelectedProperty() {
        return isSelected;
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public String getQuestion() {
        return question.get();
    }

    public SimpleStringProperty questionProperty() {
        return question;
    }

    public String getAnswer() {
        return answer.get();
    }

    public SimpleStringProperty answerProperty() {
        return answer;
    }
}