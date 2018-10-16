package com.naim.messageservice.service.repository;

import com.naim.messageservice.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public final class StoreRepository implements Store {

    final Map<String,Message> messageMap = new HashMap<>();

    @Autowired
    DBMessageRepository messageRepository;

    @Override
    public Message saveMessage(Message message) {
        messageMap.put(message.getMessageId(),message);
        return message;
    }

    @Override
    public Message getMessage(String id) {
        return messageMap.get(id);
    }

    @Override
    public List<Message> getMessages() {
        List<Message> messageList = new ArrayList<Message>(messageMap.values());
        return messageList;
    }

    @Override
    public boolean deleteMessage(String messageId) {
        Message message = getMessage(messageId);
        messageMap.remove(message.getMessageId());
        return true;
    }

    @Override
    public Message updateMessage(Message message) {
        return saveMessage(message);
    }

    @Override
    public boolean persistMessages() {
        List<Message> messageList = new ArrayList<Message>(messageMap.values());
        messageRepository.saveAll(messageList);
        return true;
    }
}
