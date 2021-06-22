package com.quizzl.app.service;

import com.quizzl.app.model.dbEntities.Flashcard;
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

    /**
     *  @return List<Flashcard> Returns List with all Flashcards
     */
    @Override
    public List<Flashcard> findAll() {
        return repository.findAll();
    }

    /**
     * @param id Id of type Long
     * @return Returns a single Flashcard
     */
    @Override
    public Flashcard findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("no Entity with Id"));
    }

    /**
     * @param flashcard Save Flashcard to DB
     */
    @Override
    public void save(Flashcard flashcard) {
        repository.save(flashcard);

    }

    /**
     * @param list Saves given Flashcard List to DB
     */
    @Override
    public void saveAll(List<Flashcard> list) {
        repository.saveAll(list);
    }

    /**
     * @param id Id of type Long to query DB
     */
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
