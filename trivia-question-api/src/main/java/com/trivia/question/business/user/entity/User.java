package com.trivia.question.business.user.entity;

import com.trivia.question.business.answer.entity.Answer;
import com.trivia.question.business.quiz.entity.Quiz;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @Basic(optional = false)
    private String email;

    @Column(nullable = false)
    @Basic(optional = false)
    private String name;

    @Column(name = "hashedPassword", nullable = false)
    @Basic(optional = false)
    private String password;

    @Column(name = "isAdmin", nullable = false)
    @Basic(optional = false)
    private boolean isAdmin;

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private List<Quiz> quizzes;


    private String salt;

    public User(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
