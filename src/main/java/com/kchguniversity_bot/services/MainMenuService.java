package com.kchguniversity_bot.services;

import com.kchguniversity_bot.bots.KchguBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainMenuService {

    private final TelegramMessageSender messageSender;

    @Autowired
    public MainMenuService(TelegramMessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendMainMenu(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText("Добро пожаловать в КЧГУ! Выберите раздел:");
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("О вузе");
        row1.add("Поступить в университет");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("");
        row2.add("");

        KeyboardRow row3 = new KeyboardRow();
        row3.add("");
        row3.add("");


        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);

        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        message.setReplyMarkup(keyboardMarkup);

        messageSender.sendMessage(message, chatId);
    }


}
