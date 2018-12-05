package kz.enu.lions.telegrambot;

import java.util.Objects;

public class QuestionFilter {
    String language;
    String status;
    String subject;

    public QuestionFilter(String language, String status, String subject) {
        this.language = language;
        this.status = status;
        this.subject = subject;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionFilter that = (QuestionFilter) o;
        return Objects.equals(language, that.language) &&
                Objects.equals(status, that.status) &&
                Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {

        return Objects.hash(language, status, subject);
    }
}
