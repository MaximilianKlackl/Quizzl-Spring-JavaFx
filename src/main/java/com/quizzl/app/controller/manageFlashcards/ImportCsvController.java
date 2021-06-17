package com.quizzl.app.controller.manageFlashcards;
import com.quizzl.app.util.CsvUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


@Component
public class ImportCsvController
{
    @FXML private TextField stapleNameField;
    @FXML private TextField descriptionField;
    @FXML private TextField topicField;
    @FXML private TextField filePathField;

    private ManageFlashcardsController controller;
    
    public void openFileBrowser(ActionEvent actionEvent) {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Export Flashcard");
        File defaultDirectory = new File("C:\\Users\\User");
        chooser.setInitialDirectory(defaultDirectory);

        Stage stage = new Stage();
        File file = chooser.showOpenDialog(stage);
        filePathField.setText(file.getAbsolutePath());
    }

    public void cancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setData(ManageFlashcardsController controller){
        this.controller = controller;
    }

    public void importList(ActionEvent actionEvent) throws IOException {

        if(!stapleNameField.getText().isEmpty() && !descriptionField.getText().isEmpty() && !topicField.getText().isEmpty() && !filePathField.getText().isEmpty()){

            CsvUtilities.importCards(filePathField.getText(), stapleNameField.getText(), descriptionField.getText(), topicField.getText());

            controller.updateStaple(stapleNameField.getText());

            Stage stage  = (Stage) filePathField.getScene().getWindow();
            stage.close();
        }
    }
}
