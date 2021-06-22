package com.quizzl.app.service;

import com.quizzl.app.model.dbEntities.Statistic;
import com.quizzl.app.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StatisticService implements IStatisticService{

    private final StatisticRepository repository;

    @Autowired
    public StatisticService(StatisticRepository repository) {
        this.repository = repository;
    }

    /**
     * @return Returns List of type Statistic
     */
    @Override
    public List<Statistic> findAll() {
        return repository.findAll();
    }

    /**
     * @param id Id of type long to query DB
     * @return Return single Statistic
     */
    @Override
    public Statistic findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("no Entity with Id"));
    }

    /**
     * @param statistic Save Entity to Db
     */
    @Override
    public void save(Statistic statistic) {
        repository.save(statistic);
    }

    /**
     * @param id Delete Entity
     */
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
