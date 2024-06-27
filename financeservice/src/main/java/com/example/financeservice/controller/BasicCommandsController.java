package com.example.financeservice.controller;

import com.example.financeservice.dto.StartDto;
import com.example.financeservice.dto.TransactionDTO;
import com.example.financeservice.service.TelegramService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/commands")
public class BasicCommandsController {

    private final TelegramService service;

    @PostMapping("start")
    public ResponseEntity<Object> start(@RequestBody StartDto startDto) {
        var ok = service.start(startDto.name(), startDto.tg_id());
        return ok.map(o -> ResponseEntity.ok().body(o))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("add_transaction")
    public ResponseEntity<Integer> addTransaction(@RequestBody TransactionDTO transactionDTO){
        return ResponseEntity.ok().body(service.addTransaction(transactionDTO));
    }
}
