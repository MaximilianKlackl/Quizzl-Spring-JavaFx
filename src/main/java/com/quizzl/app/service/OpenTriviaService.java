package com.quizzl.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.quizzl.app.model.openTrivia.Category;
import com.quizzl.app.model.openTrivia.Question;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class OpenTriviaService implements IOpenTriviaService {

    private final String URL = "https://opentdb.com/";
    private final RestTemplate restTemplate;

    OpenTriviaService(){
        this.restTemplate = new RestTemplate();
    }

    /**
     * @return Returns a List of {@link Category} using Open Trivia API
     */
    public List<Category> getAllCategories(){

        // do request
        String response = restTemplate.getForObject(URL + "api_category.php", String.class);

        // map json to objects
        ObjectMapper mapper = new ObjectMapper();
        // care for json with root name
        ObjectReader reader = mapper.readerFor(new TypeReference<List<Category>>() {}).withRootName("trivia_categories");
        List<Category> categories = null;

        try {
            categories = reader.readValue(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return categories;
    }

    /**
     * @param categoryId Id for specific Category
     * @param amount How many question to fetch
     * @param level Difficulty of the questions
     * @return Returns a List of {@link Question}
     */
    @Override
    public List<Question> getQuestionsByCategory(int categoryId, int amount, String level) {

        // Example Url: https://opentdb.com/api.php?amount=10&category=18&difficulty=medium&type=boolean
        // difficulty with empty string means not difficulty
        level = level.equals("any") ? "" : level;

        String queryParameter = "?amount=" + amount + "&category="+ categoryId + "&difficulty=" + level.toLowerCase() + "&type=boolean";
        String response = restTemplate.getForObject(URL + "api.php" + queryParameter, String.class);

        // use QuestionJsonWrapper as a wrapper class to properly parse json
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ObjectReader reader = mapper.readerFor(new TypeReference<QuestionJsonWrapper>() {});
        QuestionJsonWrapper parsed = null;

        try {
            parsed = reader.readValue(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        assert parsed != null;
        return parsed.results;
    }

    private static class QuestionJsonWrapper{
        public int response_code;
        public List<Question> results;
    }
}
