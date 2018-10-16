package com.naim.messageservice.service;

import com.naim.messageservice.model.Message;

import java.util.List;

public interface MessageService {
     public Message addMessage(Message message) throws MessageException;
     public Message getMessage(String id) throws MessageException;
     public List<Message> getMessages();
     public boolean deleteMessage(String messageId) throws MessageException;
     public Message updateMessage(Message message) throws MessageException;
     public boolean persistMessages();
}
