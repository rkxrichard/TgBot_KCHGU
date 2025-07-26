package com.kchguniversity_bot.services;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface TelegramMessageSender {
    void sendMessage(SendMessage message, Long chatId);
}
