package com.quizzl.app.util;

import com.quizzl.app.model.*;
import com.quizzl.app.repository.FlashcardStapleRepository;
import com.quizzl.app.repository.VocabListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvUtilities {

    private static FlashcardStapleRepository flashcardStapleRepository;
    private static VocabListRepository vocabListRepository;

    @Autowired
    CsvUtilities(FlashcardStapleRepository flashcardStapleRepository, VocabListRepository vocabListRepository)
    {
        CsvUtilities.flashcardStapleRepository = flashcardStapleRepository;
        CsvUtilities.vocabListRepository = vocabListRepository;

    }

    public static void exportCards(List<Card> cardList, String path)
    {
        // export to csv

    }


    public static void importCards(String path, boolean isFlashcard)
    {
      
    }
}
