package com.quizzl.app.service;

import com.quizzl.app.model.Flashcard;
import com.quizzl.app.model.Statistic;
import com.quizzl.app.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class StatisticService implements IStatisticService{

    private final StatisticRepository repository;

    @Autowired
    public StatisticService(StatisticRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Statistic> findAll() {
        return repository.findAll();
    }

    @Override
    public Statistic findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("no Entity with Id"));
    }

    @Override
    public void save(Statistic statistic) {
        repository.save(statistic);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
