package com.quizzl.app.repository;

import com.quizzl.app.model.Vocab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocabRepository extends JpaRepository<Vocab, Long> {

}
