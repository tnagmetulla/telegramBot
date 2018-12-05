package kz.enu.lions.telegrambot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class LionsBot extends TelegramLongPollingBot {

    private QuestionContainer questionContainer;
    private UserContainer userContainer;

    LionsBot(QuestionContainer questionContainer, UserContainer userContainer) {
        this.questionContainer = questionContainer;
        this.userContainer = userContainer;
    }

    public void onUpdateReceived(Update update) {

//        System.out.println("DA");
//        System.out.println(update.getMessage());
//        System.out.println(update.getMessage().getFrom());
//
        String username = "";

        if (update.hasMessage()) {
            username = update.getMessage().getFrom().getUserName();
            System.out.println(update.getMessage().getText());
        } else if (update.hasCallbackQuery()) {
            username = update.getCallbackQuery().getFrom().getUserName();
            System.out.println(update.getCallbackQuery().getData());
        }
        System.out.println(username);
        System.out.println(update.hasShippingQuery());
        System.out.println(update.hasEditedMessage());
        System.out.println(update.hasChosenInlineQuery());
        System.out.println(update.hasCallbackQuery());
        System.out.println(update.hasChannelPost());
        System.out.println(update.hasEditedChannelPost());
        System.out.println(update.hasPreCheckoutQuery());
        System.out.println(update.hasInlineQuery());

//        String message_text = update.getMessage().getText();
//        long chat_id = update.getMessage().getChatId();
//        SendMessage message = new SendMessage()
//                .setChatId(chat_id)
//                .setText(update.getMessage().getFrom().getUserName() + ", " + message_text + Emoji.AIRPLANE.toString());
//        try {
//            execute(message); // Sending our message object to user
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }

        SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId());

        if (userContainer.userExists(username)) {
            if (userContainer.getUser(username).location == Location.LANGUAGE)//todo
            {
                if (update.getMessage().getText().equals("казахский")) {
                    userContainer.getUser(username).language = "kz";
                }
                if (update.getMessage().getText().equals("русский")) {
                    userContainer.getUser(username).language = "ru";
                }

                message.setText("Вы учитесь в школе или в ВУЗе?");

                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                message.setReplyMarkup(replyKeyboardMarkup);
                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(false);
                List<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow keyboardFirstRow = new KeyboardRow();
                keyboardFirstRow.add(new KeyboardButton("студент"));
                keyboardFirstRow.add(new KeyboardButton("школьник"));
                keyboard.add(keyboardFirstRow);
                replyKeyboardMarkup.setKeyboard(keyboard);

                userContainer.getUser(username).location = Location.STATUS;
            } else if (userContainer.getUser(username).location == Location.STATUS)//todo
            {
                userContainer.getUser(username).status = "student";
//                userContainer.addUserToDB(username);
            } else {
                if (userContainer.getUser(username).location == Location.MAIN) {

                }
                if (userContainer.getUser(username).location == Location.QUESTION) {

                }
                if (userContainer.getUser(username).location == Location.FACT) {

                }
            }
        } else {
            User user = new User();
            user.setUsername(username);
            user.location = Location.LANGUAGE;
            userContainer.users.add(user);

            message.setText("Выберите язык");

            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            message.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setSelective(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(false);
            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow keyboardFirstRow = new KeyboardRow();
            keyboardFirstRow.add(new KeyboardButton("казахский"));
            keyboardFirstRow.add(new KeyboardButton("русский"));
            keyboard.add(keyboardFirstRow);
            replyKeyboardMarkup.setKeyboard(keyboard);


        }

//        if (update.hasMessage() && update.getMessage().hasText()) {
//
//            String message_text = update.getMessage().getText();
//            long chat_id = update.getMessage().getChatId();
//
//
//            SendMessage message = new SendMessage()
//                    .setChatId(chat_id)
//                    .setText(update.getMessage().getFrom().getUserName() + ", " + message_text + Emoji.AIRPLANE.toString());


//            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
//            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//            Question randomQuestion = questionContainer.getRandomQuestion("ru", "student", "История");
//            System.out.println(randomQuestion);
//
//            List<InlineKeyboardButton> firstAnswer = new ArrayList<>();
//            List<InlineKeyboardButton> secondAnswer = new ArrayList<>();
//            List<InlineKeyboardButton> thirdAnswer = new ArrayList<>();
//            List<InlineKeyboardButton> fourthAnswer = new ArrayList<>();
//            List<InlineKeyboardButton> fifthAnswer = new ArrayList<>();
//
//
//            firstAnswer.add(new InlineKeyboardButton().setText(randomQuestion.getAnswers().get(0).getName()).setCallbackData("true"));
//            secondAnswer.add(new InlineKeyboardButton().setText(randomQuestion.getAnswers().get(1).getName()).setCallbackData("false"));
//            thirdAnswer.add(new InlineKeyboardButton().setText(randomQuestion.getAnswers().get(2).getName()).setCallbackData("false"));
//            fourthAnswer.add(new InlineKeyboardButton().setText(randomQuestion.getAnswers().get(3).getName()).setCallbackData("false"));
//            fifthAnswer.add(new InlineKeyboardButton().setText(randomQuestion.getAnswers().get(4).getName()).setCallbackData("false"));
//            // Set the keyboard to the markup
//            rowsInline.add(firstAnswer);
//            rowsInline.add(secondAnswer);
//            rowsInline.add(thirdAnswer);
//            rowsInline.add(fourthAnswer);
//            rowsInline.add(fifthAnswer);
//            Collections.shuffle(rowsInline);
//            // Add it to the message
//            markupInline.setKeyboard(rowsInline);
//            message.setText(randomQuestion.getName());
//            message.setReplyMarkup(markupInline);

//
//            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//            message.setReplyMarkup(replyKeyboardMarkup);
//            replyKeyboardMarkup.setSelective(true);
//            replyKeyboardMarkup.setResizeKeyboard(true);
//            replyKeyboardMarkup.setOneTimeKeyboard(false);
//
//            // Создаем список строк клавиатуры
//            List<KeyboardRow> keyboard = new ArrayList<>();
//
//            // Первая строчка клавиатуры
//            KeyboardRow keyboardFirstRow = new KeyboardRow();
//            // Добавляем кнопки в первую строчку клавиатуры
//            keyboardFirstRow.add(new KeyboardButton("HI"));
//            keyboardFirstRow.add(new KeyboardButton("HELP"));
//
//            // Добавляем все строчки клавиатуры в список
//            keyboard.add(keyboardFirstRow);
//            // и устанваливаем этот список нашей клавиатуре
//            replyKeyboardMarkup.setKeyboard(keyboard);


        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


    public String getBotUsername() {
        return "lionsTestbot";
    }

    public String getBotToken() {
        return "591535281:AAF-oSoNzO9ze5M3XH35Iuuv_xzpF2NDRBo";
    }
}
