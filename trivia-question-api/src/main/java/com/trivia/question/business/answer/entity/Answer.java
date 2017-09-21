package com.trivia.question.business.answer.entity;

import com.trivia.question.business.choice.entity.Choice;
import com.trivia.question.business.quiz.entity.Quiz;
import com.trivia.question.business.topic.entity.Topic;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="quiz_id")
    private Quiz quiz;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="choice_id")
    private Choice choice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
