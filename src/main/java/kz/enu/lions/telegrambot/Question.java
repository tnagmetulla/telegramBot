package kz.enu.lions.telegrambot;

import java.util.ArrayList;
import java.util.List;

public class Question {
    public long id;
    public String name;
    public String status;
    public String language;
    public List<Answer> answers;
    public String subject;

    public Question() {
        this.answers = new ArrayList<>();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", language='" + language + '\'' +
                ", answers=" + answers +
                ", subject='" + subject + '\'' +
                '}';
    }
}
