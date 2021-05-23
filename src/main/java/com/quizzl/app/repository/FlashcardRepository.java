package com.quizzl.app.repository;

import com.quizzl.app.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {

}
