package com.quizzl.app.util;

import com.quizzl.app.model.Card;
import com.quizzl.app.model.Flashcard;
import com.quizzl.app.model.FlashcardStaple;
import com.quizzl.app.model.Statistic;
import com.quizzl.app.repository.FlashcardRepository;
import com.quizzl.app.repository.FlashcardStapleRepository;
import com.quizzl.app.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandLineUtil implements CommandLineRunner {

    private final FlashcardRepository flashcardRepository;
    private final FlashcardStapleRepository flashcardStapleRepository;
    private final StatisticRepository statisticRepository;

    @Autowired
    public CommandLineUtil(
            FlashcardRepository flashcardRepository,
            FlashcardStapleRepository flashcardStapleRepository,
            StatisticRepository statisticRepository
    ){

        this.flashcardRepository = flashcardRepository;
        this.flashcardStapleRepository = flashcardStapleRepository;
        this.statisticRepository = statisticRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Flashcard> flashcardList = new ArrayList<>();
        FlashcardStaple flashcardStaple = new FlashcardStaple("Test", "Test", "Test");
        Statistic statistic = new Statistic(20, 0.2f);

        flashcardStaple.setStatistic(statistic);

        for(int i = 0; i < 20; i++){
            Flashcard flashcard = new Flashcard("front" + i, "back" + i );
            flashcard.setFlashcardList(flashcardStaple);
            flashcard.setStatistic(statistic);
            flashcardList.add(flashcard);
        }

        flashcardRepository.saveAll(flashcardList);
        statisticRepository.save(statistic);
        flashcardStapleRepository.save(flashcardStaple);

    }
}
