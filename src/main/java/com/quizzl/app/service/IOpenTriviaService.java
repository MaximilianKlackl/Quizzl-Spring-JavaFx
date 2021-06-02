package com.quizzl.app.service;

import com.quizzl.app.model.openTrivia.Category;
import com.quizzl.app.model.openTrivia.Question;

import java.util.List;

public interface IOpenTriviaService {

    public List<Category> getAllCategories();
    public List<Question> getQuestionsByCategory(int categoryId, int amount, String level);
}
