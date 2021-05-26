package com.quizzl.app.service;

import com.quizzl.app.model.Flashcard;
import com.quizzl.app.repository.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FlashcardService implements IFlashcardService{

    private final FlashcardRepository repository;

    @Autowired
    public FlashcardService(FlashcardRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Flashcard> findAll() {
        return repository.findAll();
    }

    @Override
    public Flashcard findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("no Entity with Id"));
    }

    @Override
    public void save(Flashcard flashcard) {
        repository.save(flashcard);

    }

    @Override
    public void saveAll(List<Flashcard> list) {
        repository.saveAll(list);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
