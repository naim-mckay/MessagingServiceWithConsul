package com.naim.messageservice.service.repository;

import com.naim.messageservice.model.Message;

import java.util.List;

public interface Store {
    public Message saveMessage(Message message);
    public Message getMessage(String id);
    public List<Message> getMessages();
    public boolean deleteMessage(String messageId);
    public Message updateMessage(Message message);
    public boolean persistMessages();
}
