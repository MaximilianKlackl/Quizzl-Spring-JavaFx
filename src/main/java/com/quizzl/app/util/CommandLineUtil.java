package com.quizzl.app.util;

import com.quizzl.app.model.Flashcard;
import com.quizzl.app.repository.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineUtil implements CommandLineRunner {

    private final FlashcardRepository flashcardRepository;

    @Autowired
    public CommandLineUtil(FlashcardRepository flashcardRepository){
        this.flashcardRepository = flashcardRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("CommandLineRunner: ");

        Flashcard f1 = new Flashcard();

        flashcardRepository.save(f1);
        flashcardRepository.findAll().forEach(System.out::println);
    }
}
