package com.quizzl.app.repository;

import com.quizzl.app.model.VocabList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocabListRepository extends JpaRepository<VocabList, Long> {

}
