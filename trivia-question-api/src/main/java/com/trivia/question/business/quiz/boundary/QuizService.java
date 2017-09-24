package com.trivia.question.business.quiz.boundary;

import com.trivia.question.business.Constants;
import com.trivia.question.business.answer.entity.Answer;
import com.trivia.question.business.choice.entity.Choice;
import com.trivia.question.business.persistence.boundary.TriviaQuestion;
import com.trivia.question.business.question.entity.Question;
import com.trivia.question.business.quiz.control.QuizControl;
import com.trivia.question.business.quiz.control.QuizResult;
import com.trivia.question.business.quiz.control.QuizStartResult;
import com.trivia.question.business.quiz.entity.Quiz;
import com.trivia.question.business.topic.boundary.TopicService;
import com.trivia.question.business.topic.entity.Topic;
import com.trivia.question.business.user.entity.User;

import javax.ejb.EJB;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotAuthorizedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Stateless
public class QuizService{

    @Inject
    @TriviaQuestion
    protected EntityManager em;

    @EJB
    TopicService topicService;

    @EJB
    QuizControl quizControl;

    public QuizStartResult start(int topicId, int userId) throws ObjectNotFoundException {
        Topic topic = new Topic();
        User user = new User();
        if(!topicService.isTopicExist(topicId))
            throw new ObjectNotFoundException("Topic object does not exist");
        List<Question> questions = topicService.getQuestionsByTopicId(topicId, Constants.QUESTIONS_LIMIT);
        Quiz quiz = new Quiz();
        topic.setId(topicId);
        user.setId(userId);
        quiz.setTopic(topic);
        quiz.setUser(user);
        quiz.setQuestions(questions);
        quiz.setNumberOfQuestions(Constants.QUESTIONS_LIMIT);
        quiz = em.merge(quiz);
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", quiz.getId());
        data.put("questions", quiz.getQuestions());
        return QuizStartResult.success(data);
    }

    public QuizResult submit (int quizId) throws ObjectNotFoundException {
        Quiz quiz = em.find(Quiz.class, quizId);
        if(quiz == null){
            throw new ObjectNotFoundException("Quiz object does not exist");
        }
        List<Choice> userChoices = em.createNamedQuery("getQuizResult", Choice.class).setParameter("id",quizId).getResultList();
        if(userChoices.size() != quiz.getNumberOfQuestions()){
            return QuizResult.notFinished();
        }

        int score = quizControl.calculateScore(userChoices);

        quiz.setScore(score);


        em.persist(quiz);

        HashMap<String,String> data = new HashMap<>();
        data.put("score", score+"");


        return QuizResult.finished(data);
    }

    public QuizResult submitReview(int quizId) throws ObjectNotFoundException {
        Quiz quiz = em.find(Quiz.class, quizId);
        quizControl.CreateQuizReviewCase(quiz);
        quiz.setScore(-1);
        em.persist(quiz);

        HashMap<String,String> data = new HashMap<>();
        return QuizResult.finished(data);
    }

    private List<Choice> getUserChoices(int quizId) throws ObjectNotFoundException {
        Quiz quiz = em.find(Quiz.class, quizId);
        if(quiz == null){
            throw new ObjectNotFoundException("Quiz object does not exist");
        }
        return em.createNamedQuery("getQuizResult", Choice.class).setParameter("id",quizId).getResultList();
    }

    public List<Question> getAnsweredQuestions(int quizId){
        return em.createNamedQuery("getAnsweredQuestions", Question.class)
                .setParameter("quizId", quizId)
                .getResultList();
    }

    public boolean doesQuestionBelong(int quizId, int choiceId){
        Question question = em.createNamedQuery("getQuestionOfChoiceInQuiz", Question.class)
                .setParameter("quizId", quizId)
                .setParameter("choiceId", choiceId)
                .getSingleResult();
        Quiz quiz = em.find(Quiz.class, quizId);
        return quizControl.questionExists(quiz, question);
    }

    public JsonObject getScore(int quizId, int userId) throws ObjectNotFoundException {
        Quiz quiz = em.find(Quiz.class, quizId);
        if(quiz == null){
            throw new ObjectNotFoundException("Quiz object does not exist");
        }
        if(quiz.getUser().getId() != userId){
            throw new NotAuthorizedException("this user is not authorized to check this quiz");
        }
        return quizControl.getScoreInJson(quiz);
    }
}
