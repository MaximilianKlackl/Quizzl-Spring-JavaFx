package com.quizzl.app.service;

import com.quizzl.app.model.dbEntities.FlashcardStaple;

import java.util.List;

public interface IFlashcardStapleService {

    List<FlashcardStaple> findAll();
    FlashcardStaple findOne(Long id);
    void save(FlashcardStaple staple);
    void deleteById(Long id);
}
