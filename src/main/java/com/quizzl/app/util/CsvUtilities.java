package com.quizzl.app.util;

import com.quizzl.app.model.*;
import com.quizzl.app.repository.FlashcardStapleRepository;
import com.quizzl.app.repository.VocabListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvUtilities {

    private static FlashcardStapleRepository flashcardStapleRepository;
    private static VocabListRepository vocabListRepository;

    @Autowired
    CsvUtilities(FlashcardStapleRepository flashcardStapleRepository, VocabListRepository vocabListRepository){
        CsvUtilities.flashcardStapleRepository = flashcardStapleRepository;
        CsvUtilities.vocabListRepository = vocabListRepository;

    }

    public static void exportCards(List<Card> cardList, String fileName) throws IOException {


        // export to csv


    }

    public static void importCards(String path, boolean isFlashcard) throws IOException {

        List<Card> cards = new ArrayList<>();

        // csv to cards here

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {

        }

        if(isFlashcard){
            FlashcardStaple flashcardStaple = new FlashcardStaple("Default", "Default", "Default");
            flashcardStaple.setFlashcardList((List<Flashcard>)(List<?>)cards);
            flashcardStapleRepository.save(flashcardStaple);

        }

        if(!isFlashcard){
            VocabList vocabList = new VocabList("Default", "Default", "Default");
            vocabList.setVocabList((List<Vocab>)(List<?>) cards);
            vocabListRepository.save(vocabList);
        }
    }
}
