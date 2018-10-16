package com.naim.messageservice.model;

import com.naim.messageservice.controller.ConfigController;

import javax.persistence.*;
import java.util.concurrent.TimeUnit;


@Entity
public class Message {

    @Id
    String messageId;

    @Column
    String messageText;

    @Column
    String messageSender;

    @Column
    String messageReciver;

    @Column
    Long creationTime;

    public Message(){}

    public Message(String messageId, String messageText, String messageSender, String messageReciver) {
        this.messageId = messageId;
        this.messageText = messageText;
        this.messageSender = messageSender;
        this.messageReciver = messageReciver;
    }



    public void setMessageCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String sender) {
        this.messageSender = sender;
    }

    public String getMessageReciver() {
        return messageReciver;
    }

    public void setMessageReciver(String reciver) {
        this.messageReciver = reciver;
    }

    public boolean isEditable() {
        long expire = creationTime + TimeUnit.SECONDS.toMillis(ConfigController.MESSAGE_EDIT_TIMER);
        long now = System.currentTimeMillis();
        return (now < expire);
    }

    public boolean isDeleteable(){
        long deleteTime = creationTime + TimeUnit.MINUTES.toMillis(ConfigController.MESSAGE_DELETE_TIMER);
        long now = System.currentTimeMillis();
        return (deleteTime < now);
    }
}
