package com.quizzl.app.util;

import com.quizzl.app.model.dbEntities.Flashcard;
import com.quizzl.app.model.dbEntities.FlashcardStaple;
import com.quizzl.app.service.FlashcardStapleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvUtilities {

    private static FlashcardStapleService service;

    @Autowired
    CsvUtilities(FlashcardStapleService service)
    {
        CsvUtilities.service = service;
    }

    /**
     * @param staple Staple Object to export
     * @param path URI where to export
     * @throws IOException Handle IO Exception
     */
    public static void exportCards(FlashcardStaple staple, String path) throws IOException {
        List<Flashcard> flashcardList = staple.getFlashcardList();
        int listIteratorCounter = 0;

        File file = new File(path + "\\" + staple.getName() + ".csv");

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            while (flashcardList.size() > listIteratorCounter) {
                bw.write(flashcardList.get(listIteratorCounter).getQuestion());
                bw.write(";");
                bw.write(flashcardList.get(listIteratorCounter).getAnswer());
                bw.newLine();
                listIteratorCounter++;
            }
        }
    }

    /**
     * @param path URI from import File
     * @param name
     * @param description
     * @param topic
     * @throws IOException Handle IO Exception
     */
    public static void importCards(String path, String name, String description, String topic) throws IOException {

        List<Flashcard> flashcardList = new ArrayList<>();
        FlashcardStaple importedFlashcardStaple = new FlashcardStaple(name, description, topic, null,null);

        String[] data;
        String line;

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                data = line.split(";");
                
                if(data.length != 0){
                    String question = data[0];
                    String answer = data[1];

                    Flashcard flashcard = new Flashcard(question, answer, null, importedFlashcardStaple);
                    flashcardList.add(flashcard);
                }
            }
        }

        importedFlashcardStaple.setFlashcardList(flashcardList);
        service.save(importedFlashcardStaple);
    }
}
