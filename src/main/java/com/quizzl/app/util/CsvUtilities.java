package com.quizzl.app.util;

import com.quizzl.app.model.Card;
import com.quizzl.app.repository.FlashcardStapleRepository;
import com.quizzl.app.repository.VocabListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvUtilities {

    private FlashcardStapleRepository flashcardStapleRepository;
    private VocabListRepository vocabListRepository;

    @Autowired
    CsvUtilities(FlashcardStapleRepository flashcardStapleRepository, VocabListRepository vocabListRepository){
        flashcardStapleRepository = flashcardStapleRepository;
        vocabListRepository = vocabListRepository;

    }

    public void exportCards(List<Card> cardList){
        // export to csv
    }

    public void importCards(URL url, boolean isFlashcard){

        List<Card> cards = new ArrayList<>();

        // csv to cards here

        if(isFlashcard){
            // flashcardStapleRepository.save();

        }

        if(!isFlashcard){
            // vocabListRepository.save();
        }
    }
}
