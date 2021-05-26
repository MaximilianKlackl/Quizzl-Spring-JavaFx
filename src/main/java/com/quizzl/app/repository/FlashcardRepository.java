package com.quizzl.app.repository;

import com.quizzl.app.model.Flashcard;
import com.quizzl.app.model.FlashcardStaple;
import com.quizzl.app.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {

    @Override
    @Modifying
    @Query(name = "DELETE FROM flashcard WHERE id = ?1", nativeQuery = true)
    void deleteById(Long aLong);

}
