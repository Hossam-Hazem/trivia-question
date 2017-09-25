package com.trivia.question.business.quiz.control;

import com.trivia.question.CaseManagerConnector;
import com.trivia.question.business.answer.entity.Answer;
import com.trivia.question.business.choice.entity.Choice;
import com.trivia.question.business.question.entity.Question;
import com.trivia.question.business.quiz.entity.Quiz;
import org.apache.commons.json.JSONArray;
import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;

import javax.ejb.Stateless;
import javax.json.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class QuizControl {

    public int calculateScore(List<Choice> userChoices){
        int score = 0;
        for(Choice userChoice : userChoices){
            if(userChoice.isCorrect())
                score++;
        }
        return score;
    }

    public boolean questionExists(Quiz quiz, Question question) {
        for(Question quizQuestion : quiz.getQuestions()){
            if(quizQuestion.equals(question)){
                return true;
            }
        }
        return false;
    }

    public void CreateQuizReviewCase(Quiz quiz){
//        List<Answer> answers = quiz.getAnswers();
        CaseManagerConnector caseManagerConnector = new CaseManagerConnector();
        List<String> questionsStr = new LinkedList<>();
        List<String> answersStr = new LinkedList<>();
        for(Question question : quiz.getQuestions()){
            questionsStr.add(question.getName());
        }
        for(Answer answer : quiz.getAnswers()){
            answersStr.add(answer.getChoice().getText());
        }
        System.out.println(quiz);
        caseManagerConnector.createReviewQuizCase(quiz.getUser().getId(), quiz.getId(), questionsStr, answersStr);
    }

    public JsonObject getScoreInJson(Quiz quiz) {
        return Json.createObjectBuilder().add("score", quiz.getScore()).build();
    }


    public JSONArray getJsonFromCollection(Collection<Quiz> quizzes) {
        JSONArray jsonArray = new JSONArray();
        for(Quiz quiz : quizzes){
            int score = quiz.getScore() != null ? quiz.getScore() : 0;
            JSONObject jsonObject = new JSONObject();
            int questionsCount = quiz.getNumberOfQuestions() == null ? -1 : quiz.getNumberOfQuestions();
            try {
                jsonObject.put("id", quiz.getId());
                jsonObject.put("score", score);
                jsonObject.put("topic", quiz.getTopic().getName());
                jsonObject.put("questionsCount", questionsCount);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
