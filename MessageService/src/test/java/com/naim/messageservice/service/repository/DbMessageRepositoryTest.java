package com.naim.messageservice.service.repository;

import com.naim.messageservice.model.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(properties = {"spring.cloud.consul.config.enabled=false"})
public class DbMessageRepositoryTest {


    @Autowired
    private DBMessageRepository repository;

    Store store;
    Message testMessage,secMessage;

    @Before
    public void setUp() throws Exception {
        store = new StoreRepository();
        testMessage = new Message("messageId","Hello","fromMe","toYou");
        secMessage = new Message("newmessageId","good bye","fromMe","tosomeOne");
    }

    @Test
    public void test_storeSaveAllMessages_DbRepo(){
        List<Message> list = Arrays.asList(testMessage,secMessage);
        repository.saveAll(list);
        Message message = repository.findById("messageId").get();
        assertEquals("Hello", message.getMessageText());

    }
}
