package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

@Component
public class EngToFrenchDictionary {
     private boolean addNewWordCheck = false;
     private String frenchWord;
     private String englishWord;

     @Autowired
     private TgDictionaryBot tgDictionaryBot;

     private  Map<String, String> engToFrenchDictionary = new HashMap<>();
    {
        engToFrenchDictionary.put("Hello", "Bonjour");
        engToFrenchDictionary.put("Cat","Chat");
    }

    public Map<String, String> getEngToFrenchDictionary() {
        return engToFrenchDictionary;
    }


    public String getTranslation(String text) {
        return engToFrenchDictionary.get(text);
    }

    public void addNewWord(String frenchWord, String englishWord){
        engToFrenchDictionary.put(frenchWord,englishWord);
    }

    public void addNewWord(Message message) {
        if (addNewWordCheck == true) {
            frenchWord = message.getText();
            tgDictionaryBot.sendMessage("Enter a TRANSLATION for new french word");

            tgDictionaryBot.onUpdateReceived(new Update());
            englishWord = message.getText();
            engToFrenchDictionary.put(frenchWord, englishWord);
            tgDictionaryBot.sendMessage("New Word added to the dictionary");
        }

    }
}






