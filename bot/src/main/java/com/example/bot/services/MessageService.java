package com.example.bot.services;

import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageService {
    public void handleMessage(Update update) {
        System.out.println("Handled! " + update);
    }
}
