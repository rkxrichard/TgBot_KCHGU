package com.kchguniversity_bot.services;

import com.kchguniversity_bot.bots.KchguBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainMenuService {

    private final TelegramMessageSender messageSender;
    private final KeyboardBuilder keyboardBuilder;

    @Autowired
    public MainMenuService(TelegramMessageSender messageSender, KeyboardBuilder keyboardBuilder) {
        this.messageSender = messageSender;
        this.keyboardBuilder = keyboardBuilder;
    }

    public void sendMainMenu(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText("Добро пожаловать в КЧГУ!");
        message.setReplyMarkup(keyboardBuilder.createMainMenu());

        messageSender.sendMessage(message, chatId);
    }


}
