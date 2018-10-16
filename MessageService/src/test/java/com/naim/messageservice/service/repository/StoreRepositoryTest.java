package com.naim.messageservice.service.repository;

import com.naim.messageservice.controller.MessageController;
import com.naim.messageservice.model.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public final class StoreRepositoryTest {


    @Mock
    DBMessageRepository messageRepository;

    Store store;
    Message testMessage,secMessage;

    @Before
    public void setUp() throws Exception {
        store = new StoreRepository();
        testMessage = new Message("messageId","Hello","fromMe","toYou");
        secMessage = new Message("newmessageId","good bye","fromMe","tosomeOne");
    }


    @Test
    public void test_SaveAnd_Get_Message() {
        store.saveMessage(testMessage);
        Message message = store.getMessage("messageId");
        assertNotNull(message);
    }

    @Test
    public void test_getMessages() {
        store.saveMessage(testMessage);
        store.saveMessage(secMessage);
        List<Message> messageList = store.getMessages();
        assertEquals(2,messageList.size());
    }

    @Test
    public void test_deleteMessage() {
        store.saveMessage(testMessage);
        store.saveMessage(secMessage);
        store.deleteMessage(testMessage.getMessageId());
        List<Message> messageList = store.getMessages();
        assertEquals(1,messageList.size());
    }

}
