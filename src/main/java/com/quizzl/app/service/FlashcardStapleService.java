package com.quizzl.app.service;

import com.quizzl.app.model.FlashcardStaple;
import com.quizzl.app.repository.FlashcardStapleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FlashcardStapleService implements  IFlashcardStapleService{

    private final FlashcardStapleRepository repository;

    @Autowired
    public FlashcardStapleService(FlashcardStapleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FlashcardStaple> findAll() {
        return repository.findAll();
    }

    @Override
    public FlashcardStaple findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("no Entity with Id"));
    }

    @Override
    public void save(FlashcardStaple staple) {
        repository.save(staple);
    }


    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
