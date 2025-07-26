package com.kchguniversity_bot.bots;

import com.kchguniversity_bot.services.MainMenuService;
import com.kchguniversity_bot.services.TelegramMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class KchguBot extends TelegramLongPollingBot implements TelegramMessageSender {

    private final MainMenuService mainMenuService;

    @Autowired
    public KchguBot(@Lazy MainMenuService mainMenuService) {
        this.mainMenuService = mainMenuService;
    }

    @Override
    public void sendMessage(SendMessage message, Long chatId) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки сообщения", e);
        }
    }

    @Override
    public String getBotUsername() {
        return "KCHGUniversityBot";
    }

    @Override
    public String getBotToken() {
        return "7556244493:AAEHnXtXCCmPDuPmrl5EPTMHgvjd0UGH1Y0";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            if ("/start".equals(text)) {
                // Создаем сообщение прямо здесь или через фабрику
                SendMessage message = new SendMessage();
                message.setChatId(update.getMessage().getChatId().toString());
                message.setText("Главное меню");
                mainMenuService.sendMainMenu(update.getMessage().getChatId());
            }
        }

    }

    private static final Logger log = LoggerFactory.getLogger(KchguBot.class);
    public void safeExecute(SendMessage message, Long chatId) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Failed to send message to chat {}: {}", chatId, e.getMessage());
            sendErrorMessage(chatId);
        }
    }

    private void sendErrorMessage(Long chatId) {
        SendMessage errorMsg = new SendMessage(
                chatId.toString(),
                "⚠️ Не удалось отправить сообщение. Пожалуйста, попробуйте позже."
        );
        try {
            execute(errorMsg);
        } catch (TelegramApiException ex) {
            log.error("Failed to send error notification", ex);
        }
    }
}