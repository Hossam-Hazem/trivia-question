package com.trivia.question.business.topic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trivia.question.business.question.entity.Question;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "topics")
public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    @Basic(optional = false)
    private String name;

    //TODO cascade
    @ManyToMany
    @JoinTable(
            name="topics_questions",
            joinColumns=@JoinColumn(name="topic_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="question_id", referencedColumnName="id"))
    private List<Question> questions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question){
        questions.add(question);
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(id, topic.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
