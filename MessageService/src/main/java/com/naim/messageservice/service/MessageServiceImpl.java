package com.naim.messageservice.service;

import com.naim.messageservice.model.Message;
import com.naim.messageservice.service.repository.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    Store store;
    @Autowired
    UuidService  uuidService;

    @Override
    public Message addMessage(Message message) throws MessageException{
        if(message.getMessageId()==null ||
                message.getMessageId().equalsIgnoreCase("null") ||
                message.getMessageId().isEmpty()){
            String messageId = uuidService.generateUUID();

            message.setMessageId(messageId);
            message.setMessageCreationTime(System.currentTimeMillis());
            return store.saveMessage(message);
        }else {
            throw new MessageException(MessageErrors.MESSAGE_NOT_ADDED);
        }
    }

    @Override
    public Message getMessage(String id) throws MessageException {
        Message message = store.getMessage(id);
        if(message==null){
            throw new MessageException(MessageErrors.MESSAGE_NOT_FOUND);
        }
        return message;
    }

    @Override
    public List<Message> getMessages() {
        return store.getMessages();
    }

    @Override
    public boolean deleteMessage(String messageId) throws MessageException{
        Message theMessage = getMessage(messageId);
        if(theMessage==null || !theMessage.isDeleteable()){
            throw new MessageException(MessageErrors.MESSAGE_COULD_NOT_BE_DELETED);
        }
        return store.deleteMessage(messageId);
    }

    @Override
    public Message updateMessage(Message message) throws MessageException{
        Message theMessage = getMessage(message.getMessageId());
        if(theMessage==null || !theMessage.isEditable()){
            throw new MessageException(MessageErrors.MESSAGE_COULD_NOT_BE_UPDATED);
        }else {
            theMessage.setMessageReciver(message.getMessageReciver());
            theMessage.setMessageSender(message.getMessageSender());
            theMessage.setMessageText(message.getMessageText());
        }
        return theMessage;  //store.updateMessage(message);
    }

    @Override
    public boolean persistMessages() {
        return store.persistMessages();
    }
}
