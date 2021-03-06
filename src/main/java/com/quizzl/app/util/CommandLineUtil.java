package com.quizzl.app.util;
import com.quizzl.app.model.dbEntities.Flashcard;
import com.quizzl.app.model.dbEntities.FlashcardStaple;
import com.quizzl.app.model.dbEntities.Statistic;
import com.quizzl.app.service.IFlashcardService;
import com.quizzl.app.service.IFlashcardStapleService;
import com.quizzl.app.service.IOpenTriviaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class CommandLineUtil implements CommandLineRunner {

    private final IFlashcardService flashcardService;
    private final IFlashcardStapleService flashcardStapleService;
    private final IOpenTriviaService openTriviaService;

    @Autowired
    public CommandLineUtil(IFlashcardService flashcardService, IFlashcardStapleService flashcardStapleService, IOpenTriviaService openTriviaService) {
        this.flashcardService = flashcardService;
        this.flashcardStapleService = flashcardStapleService;
        this.openTriviaService = openTriviaService;
    }

    @Override
    public void run(String... args) throws Exception {

        createDataSetWithPrefix("Nice");
        createDataSetWithPrefix("Test");
        createDataSetWithPrefix("Brrrr");
    }

    public void createDataSetWithPrefix(String prefix) throws IOException {

        FlashcardStaple staple = new FlashcardStaple("Test"+ prefix, "Test" + prefix, "Test" +prefix, null, new ArrayList<>());
        Statistic statistic = new Statistic(staple, null, 20, 0.2f);
        staple.setStatistic(statistic);

        for(int i = 0; i < 20; i++){
            staple.getFlashcardList().add(new Flashcard("Test" + prefix + i, "Test" + prefix + i, null, staple));
        }

        flashcardStapleService.save(staple);
    }
}
