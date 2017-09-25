package com.trivia.question.business.quiz.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import com.trivia.question.business.answer.entity.Answer;
import com.trivia.question.business.choice.entity.Choice;
import com.trivia.question.business.question.entity.Question;
import com.trivia.question.business.topic.entity.Topic;
import com.trivia.question.business.user.entity.User;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="quizzes")
@NamedQueries({
        @NamedQuery(name = "getQuizResult", query = "SELECT c FROM Quiz q JOIN q.answers a JOIN a.choice c WHERE q.id = :id"),
        @NamedQuery(name = "getAnsweredQuestions", query = "SELECT q FROM Quiz quiz JOIN quiz.answers a JOIN a.choice c JOIN  c.question q WHERE quiz.id = :quizId"),
        @NamedQuery(name = "getQuestionOfChoiceInQuiz", query = "SELECT q FROM Quiz quiz JOIN quiz.questions q JOIN q.choices c WHERE quiz.id = :quizId AND c.id = :choiceId")
})
public class Quiz implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy="quiz", fetch = FetchType.EAGER)
    private List<Answer> answers;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="topic_id")
    private Topic topic;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Basic(optional = false)
    @Column(name = "created_at", insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at = new Date();

    private Integer score;

    private Integer numberOfQuestions;

    @ManyToMany
    @JoinTable(
            name="quizzes_questions",
            joinColumns=@JoinColumn(name="quiz_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="question_id", referencedColumnName="id"))
    private List<Question> questions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @XmlTransient
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(id, quiz.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
