package com.quizzl.app.controller;

import com.quizzl.app.util.CsvUtilities;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ImportCsvController
{
    @FXML
    private TextField filePath;

    @FXML
    private void import_list() throws IOException {
        //CsvUtilities.importCards(filePath.getText());
    }
}
