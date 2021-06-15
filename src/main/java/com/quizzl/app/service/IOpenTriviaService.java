package com.quizzl.app.service;

import com.quizzl.app.model.openTrivia.Category;
import com.quizzl.app.model.openTrivia.Question;

import java.util.List;

public interface IOpenTriviaService {

    List<Category> getAllCategories();
    List<Question> getQuestionsByCategory(int categoryId, int amount, String level);
}
