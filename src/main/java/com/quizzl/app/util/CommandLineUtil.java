package com.quizzl.app.util;
import com.quizzl.app.model.dbEntities.Flashcard;
import com.quizzl.app.model.dbEntities.FlashcardStaple;
import com.quizzl.app.service.IFlashcardService;
import com.quizzl.app.service.IFlashcardStapleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CommandLineUtil implements CommandLineRunner {

    private final IFlashcardService flashcardService;
    private final IFlashcardStapleService flashcardStapleService;

    @Autowired
    public CommandLineUtil(IFlashcardService flashcardService, IFlashcardStapleService flashcardStapleService) {
        this.flashcardService = flashcardService;
        this.flashcardStapleService = flashcardStapleService;
    }

    @Override
    public void run(String... args) throws Exception {

        createDataSetWithPrefix("Nice");
        createDataSetWithPrefix("Test");
        createDataSetWithPrefix("Brrrr");

    }

    public void createDataSetWithPrefix(String prefix){

        FlashcardStaple staple = new FlashcardStaple("Test"+ prefix, "Test" + prefix, "Test" +prefix, null, new ArrayList<>());

        for(int i = 0; i < 20; i++){
            staple.getFlashcardList().add(new Flashcard("Test" + prefix, "Test" + prefix, null, staple));
        }

        flashcardStapleService.save(staple);
    }
}
