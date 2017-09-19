package com.trivia.question.business.question.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trivia.question.business.choice.entity.Choice;
import com.trivia.question.business.topic.entity.Topic;

import java.io.Serializable;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "QUESTIONS")
public class Question implements Serializable {

    private static final long serialVersionUID = -1692704940493230413L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy="questions", fetch = FetchType.EAGER)
    private List<Topic> topics;

    //TODO cascade
    @OneToMany(mappedBy="question", fetch = FetchType.EAGER)
    private List<Choice> choices;

    public Question() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String id) {
        this.name = id;
    }

    @JsonIgnore
    public List<Topic> getTopics() {
        return topics;
    }

    public void addTopic(Topic topic){
        this.topics.add(topic);
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }


    public List<Choice> getChoices() {
        return choices;
    }

    public void addChoice(Choice choice){
        choices.add(choice);
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Question)) {
            return false;
        }
        final Question other = (Question) obj;
        return Objects.equals(this.getId(), other.getId());
    }
}
