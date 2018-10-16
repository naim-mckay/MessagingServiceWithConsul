package com.naim.messageservice.controller;


import com.naim.messageservice.model.Message;
import com.naim.messageservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping(value = "/messages",
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message addMessage(@RequestBody Message message){
        return messageService.addMessage(message);
    }

    @GetMapping("/messages/{id}")
    public Message getMessage(@PathVariable String id) {
        return messageService.getMessage(id);
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return messageService.getMessages();
    }

    @DeleteMapping("/messages/{id}")
    public boolean deleteMessage(@PathVariable String id) {
        return messageService.deleteMessage(id);
    }

    @PutMapping("/messages")
    public Message updateMessage(@RequestBody Message message) {
        return messageService.updateMessage(message);
    }

    @PutMapping("/messages/persist")
    public boolean persistMessages() {
        return messageService.persistMessages();
    }
}