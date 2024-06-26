package com.example.bot.services;

import com.example.bot.commands.StartCommand;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.bot.configs.CommandsConfig.commands;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final TelegramBot telegramBot;

    public void handleMessage(Update update) {
        long chatId = update.message().chat().id();
        String chatMessage = update.message().text();

        StartCommand startCommand = (StartCommand) commands.getFirst();

        telegramBot.execute(startCommand.handle(update));
    }
}
