package com.quizzl.app.util;

import com.quizzl.app.model.Flashcard;
import com.quizzl.app.model.FlashcardStaple;
import com.quizzl.app.repository.FlashcardStapleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class CsvUtilities {

    private static FlashcardStapleRepository flashcardStapleRepository;

    @Autowired
    CsvUtilities(FlashcardStapleRepository flashcardStapleRepository)
    {
        CsvUtilities.flashcardStapleRepository = flashcardStapleRepository;

    }

    public static void exportCards(FlashcardStaple staple, String path) throws IOException {
        List<Flashcard> flashcardList = staple.getFlashcardList();
        int listIteratorCounter = 0;

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            while (flashcardList.size() > listIteratorCounter) {
                bw.write(flashcardList.get(listIteratorCounter).getQuestion());
                bw.write(";");
                bw.write(flashcardList.get(listIteratorCounter).getAnswer());
                bw.newLine();
                listIteratorCounter++;
            }
        }
    }


    public static FlashcardStaple importCards(String path) throws IOException {
        FlashcardStaple importedFlashcardStaple = new FlashcardStaple();
        String[] data;
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                data = line.split(";");
                for(String part : data) {
                    importedFlashcardStaple.getFlashcardList.add(part);
                }
            }
        }
        return importedFlashcardStaple;
    }
}
