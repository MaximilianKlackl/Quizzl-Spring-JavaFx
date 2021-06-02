package com.quizzl.app.service;


import com.quizzl.app.model.dbEntities.Flashcard;

import java.util.List;

public interface IFlashcardService {

    List<Flashcard> findAll();
    Flashcard findOne(Long id);
    void save(Flashcard flashcard);
    void saveAll(List<Flashcard> list);
    void deleteById(Long id);
}
