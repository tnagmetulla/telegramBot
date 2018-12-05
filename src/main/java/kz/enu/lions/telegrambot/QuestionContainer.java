package kz.enu.lions.telegrambot;

import java.util.*;

public class QuestionContainer {
    public List<Question> questions;
    public Map<QuestionFilter, List<Question>> filteredQuestionList;
    private DaoObject daoObject;


    QuestionContainer(DaoObject daoObject) {
        questions = new ArrayList<>();
        this.daoObject = daoObject;
        initQuestions();
    }

    public void initQuestions() {
        this.questions = this.daoObject.getAllQuestions();
        filteredQuestionList = new HashMap<>();

        for (Question question : questions) {
            QuestionFilter questionFilter = new QuestionFilter(question.language, question.status, question.subject);

            if (!filteredQuestionList.containsKey(questionFilter)) {
                List<Question> tmpList = new ArrayList<>();
                tmpList.add(question);
                filteredQuestionList.put(questionFilter, tmpList);
            } else {
                List<Question> tmpList = filteredQuestionList.get(questionFilter);
                tmpList.add(question);
                filteredQuestionList.put(questionFilter, tmpList);
            }
        }

        System.out.println(filteredQuestionList);
    }

    public Question getRandomQuestion(String lang, String status, String subject) {
        QuestionFilter questionFilter = new QuestionFilter(lang, status, subject);

        return filteredQuestionList.get(questionFilter).get(
                new Random().nextInt(filteredQuestionList.get(questionFilter).size()));
    }


}
