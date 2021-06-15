package com.quizzl.app.service;

import com.quizzl.app.model.dbEntities.FlashcardStaple;
import com.quizzl.app.repository.FlashcardStapleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FlashcardStapleService implements  IFlashcardStapleService{

    private final FlashcardStapleRepository repository;

    @Autowired
    public FlashcardStapleService(FlashcardStapleRepository repository) {
        this.repository = repository;
    }

    /**
     * @return Returns List of all Repositories
     */
    @Override
    public List<FlashcardStaple> findAll() {
        return repository.findAll();
    }

    /**
     * @param id
     * @return FlashcardStaple
     */
    @Override
    public FlashcardStaple findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("no Entity with Id"));
    }

    /**
     * @param staple Needs given Staple to save to DB
     */
    @Override
    public void save(FlashcardStaple staple) {
        repository.save(staple);
    }

    /**
     * @param id Needs id of type Long
     */
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
