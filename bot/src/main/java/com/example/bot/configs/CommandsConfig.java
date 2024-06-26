package com.example.bot.configs;

import com.example.bot.commands.Command;
import com.example.bot.commands.StartCommand;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SetMyCommands;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CommandsConfig {

    public static List<Command> commands = new ArrayList<>();

    public CommandsConfig(@NotNull TelegramBot telegramBot) {
        commands.add(new StartCommand());
        telegramBot.execute(this.createCommandMenu());
    }

    public SetMyCommands createCommandMenu() {
        return new SetMyCommands(commands.stream().map(command -> new BotCommand(
                command.name(),
                command.description()
        )).toArray(BotCommand[]::new));
    }
}
