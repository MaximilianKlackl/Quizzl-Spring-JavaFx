package com.quizzl.app.util;

import com.quizzl.app.model.FlashcardStaple;
import com.quizzl.app.repository.FlashcardStapleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CsvUtilities {

    private static FlashcardStapleRepository flashcardStapleRepository;

    @Autowired
    CsvUtilities(FlashcardStapleRepository flashcardStapleRepository)
    {
        CsvUtilities.flashcardStapleRepository = flashcardStapleRepository;

    }

    public static void exportCards(FlashcardStaple staple, String path)
    {
        // export to csv
    }


    public static FlashcardStaple importCards(String path)
    {
       //import from csv

        return null;
    }
}
