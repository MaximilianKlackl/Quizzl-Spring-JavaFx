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

        Flashcard f1 = new Flashcard("front1", "back1");
        Flashcard f2 = new Flashcard("front2", "back2");
        Flashcard f3 = new Flashcard("front3", "back3");

        List<Flashcard> flashcardList = new ArrayList<>();
        flashcardList.add(f1);
        flashcardList.add(f2);
        flashcardList.add(f3);

        FlashcardStaple fs1 = new FlashcardStaple("Test1", "Test1", "Test1");
        fs1.setFlashcardList(flashcardList);

        Statistic statistic = new Statistic(20, 0.8f, (List<Card>)(List<?>) flashcardList, fs1);
        fs1.setStatistic(statistic);

        for (Flashcard f: flashcardList) {
            f.setFlashcardList(fs1);
            f.setStatistic(statistic);
            flashcardRepository.save(f);
        }

        statisticRepository.save(statistic);
        flashcardStapleRepository.save(fs1);
    }
}
