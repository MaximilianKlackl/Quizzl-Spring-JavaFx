package com.quizzl.app.repository;

import com.quizzl.app.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {

    @Modifying
    @Query(value = "DELETE FROM card WHERE DTYPE = 'Flashcard' AND id = ?1", nativeQuery = true)
    void deleteById(Long aLong);

    @Query(value = "SELECT * FROM card WHERE DTYPE = 'Flashcard' AND id = ?1", nativeQuery = true)
    Flashcard getOneById(Long aLong);
}
