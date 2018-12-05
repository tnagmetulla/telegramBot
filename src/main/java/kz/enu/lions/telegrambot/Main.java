package kz.enu.lions.telegrambot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {

        DaoObject daoObject = new DaoObject();
        daoObject.init();

        QuestionContainer questionContainer = new QuestionContainer(daoObject);
        UserContainer userContainer = new UserContainer(daoObject);

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new LionsBot(questionContainer,userContainer));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}