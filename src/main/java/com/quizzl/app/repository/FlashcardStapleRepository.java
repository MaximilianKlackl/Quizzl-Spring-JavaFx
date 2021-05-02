package com.quizzl.app.repository;

import com.quizzl.app.model.FlashcardStaple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardStapleRepository extends JpaRepository<FlashcardStaple, Long> {

}

