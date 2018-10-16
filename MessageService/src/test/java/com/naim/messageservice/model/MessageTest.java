package com.naim.messageservice.model;


import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MessageTest {


    Message testMessage;


    @Before
    public void setUp() throws Exception {
        testMessage = new Message("messageId","Hello","fromMe","toYou");
    }

    @Test
    public void test_isEditable_BeforeTimeElapse_ShouldReturnTrue(){
        Long creation = System.currentTimeMillis();
        testMessage.setMessageCreationTime(creation);
        boolean result = testMessage.isEditable();
        assertTrue(result);
    }

    @Test
    public void test_isEditable_AfterTimeElapse_ShouldReturnFalse(){
        Long creation = System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(11);
        testMessage.setMessageCreationTime(creation);
        boolean result = testMessage.isEditable();
        assertFalse(result);
    }

    @Test
    public void test_isDeleteable_AfterTimeElapse_ShouldReturnTrue(){
        Long creation = System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(5);
        testMessage.setMessageCreationTime(creation);
        boolean result = testMessage.isDeleteable();
        assertTrue(result);
    }

    @Test
    public void test_isDeleteable_BeforeTimeElapse_ShouldReturnFalse(){
        Long creation = System.currentTimeMillis();
        testMessage.setMessageCreationTime(creation);
        boolean result = testMessage.isDeleteable();
        assertFalse(result);
    }
}
