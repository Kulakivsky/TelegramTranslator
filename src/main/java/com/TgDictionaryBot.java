package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Logger;

@Component
public class TgDictionaryBot extends TelegramLongPollingBot {

    private static final Logger LOGGER = Logger.getLogger(TgDictionaryBot.class.getName());

    private static final String BOT_TOKEN = "5674632523:AAG2xGaEhs52-EMXeOVcPqfOk3WLTxeky_o";
    private static final String BOT_USERNAME = "ivan_tutorial_java_bot";

    private Message message;

    @Autowired
    private FrenchToEnglishTranslationImpl frToEngImpl;

    @Autowired
    private ParseWordImpl parseWord;

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {


        if (update.hasMessage()) {
            message = update.getMessage();

            if (message.getText().contains("/add_french")) {
                // TODO class that will parse message.getText() into two words <eng_word> <french_word>
                parseWord.parseFrenchAndEngWord(message.getText());
            }

            if (message.hasText()) {
                sendMessage(frToEngImpl.engToFrTranslate(message.getText()));
            }
        }
    }

    private boolean addWord = false;

//    public void userInteraction(Update update) {
//        update.getMessage().getFrom().getIsBot();
//    }


    public void sendMessage(String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(message.getChatId());
        try {
          execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
