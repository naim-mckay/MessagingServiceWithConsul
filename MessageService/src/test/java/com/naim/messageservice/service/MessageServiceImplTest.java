package com.naim.messageservice.service;

import com.naim.messageservice.controller.MessageController;
import com.naim.messageservice.model.Message;
import com.naim.messageservice.service.repository.Store;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MessageServiceImplTest {

    @Mock
    Store store;
    @Mock
    UuidService  uuidService;

    @InjectMocks
    MessageServiceImpl messageService;

    Message testMessage,secMessage,noIdMessage;

    @Before
    public void setUp() throws Exception {
        noIdMessage = new Message(null,"Hello","fromMe","toYou");
        testMessage = new Message("messageId","Hello","fromMe","toYou");
        secMessage = new Message("newmessageId","good bye","fromMe","tosomeOne");
    }

    @Test(expected = MessageException.class)
    public void test_addMessage_WithID_ShouldThrowExcepton(){
        Message message = messageService.addMessage(testMessage);
    }

    @Test
    public void test_addMessage_WithNoID_Should()throws Exception{
        when(uuidService.generateUUID()).thenReturn("messageId");
        when(store.saveMessage(anyObject())).thenReturn(testMessage);
        Message message = messageService.addMessage(noIdMessage);
        assertEquals("messageId",message.getMessageId());
    }


    @Test(expected = MessageException.class)
    public void test_getMessage_WithIdNotFound_ShouldThrowExcepton(){
        Message message = messageService.getMessage("badID");
    }

    @Test
    public void test_getMessage_WithIdFound_ShouldReturnMessage(){
        when(store.getMessage(anyObject())).thenReturn(testMessage);
        Message message = messageService.getMessage("messageId");
        assertEquals("messageId",message.getMessageId());
    }


    @Test(expected = MessageException.class)
    public void test_deleteMessage_WithIdNotFound_ShouldThrowExcepton(){
        messageService.deleteMessage("badID");
    }

}
