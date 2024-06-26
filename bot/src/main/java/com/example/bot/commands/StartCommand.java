package com.example.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class StartCommand implements Command {
    public static String NAME = "/start";
    public static String DESCRIPTION = "зарегистрироваться в боте";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String description() {
        return DESCRIPTION;
    }

    @Override
    public SendMessage handle(Update update) {
        long chatId = update.message().chat().id();
        String chatMessage = update.message().text();
        return new SendMessage(chatId, "Hello world! You wrote: " + chatMessage);
    }
}
