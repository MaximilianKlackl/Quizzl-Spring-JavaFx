package com.quizzl.app.util;

import com.quizzl.app.model.Flashcard;
import com.quizzl.app.repository.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CommandLineUtil implements CommandLineRunner {

    @Autowired
    private FlashcardRepository flashcardRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("CommandLineRunner: ");

        Flashcard f1 = new Flashcard();

        flashcardRepository.save(f1);
        flashcardRepository.findAll().forEach(System.out::println);

    }
}
