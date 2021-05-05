package com.quizzl.app.util;

import com.quizzl.app.model.*;
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
        this.flashcardStapleRepository = flashcardStapleRepository;
        this.vocabListRepository = vocabListRepository;

    }

    public void exportCards(List<Card> cardList){
        // export to csv
    }

    public void importCards(URL url, boolean isFlashcard){

        List<Card> cards = new ArrayList<>();

        // csv to cards here

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
