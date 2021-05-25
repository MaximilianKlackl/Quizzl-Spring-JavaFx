package com.quizzl.app.service;

import com.quizzl.app.model.Flashcard;
import com.quizzl.app.model.Statistic;

import java.util.List;

public interface IStatisticService {

    List<Flashcard> findAll();
    Flashcard findOne(Long id);
    void save(Statistic statistic);
    void deleteById(Long id);
}