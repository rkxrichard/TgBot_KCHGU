package com.kchguniversity_bot.configuration;

import com.kchguniversity_bot.bots.KchguBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotConfiguration {
    @Bean
    public TelegramBotsApi telegramBotsApi(KchguBot bot) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(bot); // Регистрация бота
        System.out.println("Бот зарегистрирован!");
        return botsApi;
    }

    @Bean
    public KchguBot kchguBot() {
        return new KchguBot();
    }
}
