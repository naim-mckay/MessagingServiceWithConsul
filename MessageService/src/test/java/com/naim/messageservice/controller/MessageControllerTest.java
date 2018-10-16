package com.naim.messageservice.controller;

import com.naim.messageservice.model.Message;
import com.naim.messageservice.service.MessageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class MessageControllerTest {

    @InjectMocks
    MessageController controller;

    @Mock
    MessageService messageService;


    private MockMvc mockMvc;

    Message testMessage,secMessage,noIdMessage;

    @Before
    public void setUp() throws Exception {
        noIdMessage = new Message(null,"Hello","fromMe","toYou");
        testMessage = new Message("messageId","Hello","fromMe","toYou");
        secMessage = new Message("newmessageId","good bye","fromMe","tosomeOne");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void test_addMessage() throws Exception{
        String json = "{\"messageText\":\"hello\",\"sender\":\"Gupta\",\"reciver\":\"naim\"}";
        when(messageService.addMessage(testMessage)).thenReturn(testMessage);
        RequestBuilder builder = post("/messages").
                contentType(MediaType.APPLICATION_JSON).content(json);

        mockMvc.perform(builder)
                .andExpect(status().isOk());
        verify(messageService, Mockito.times(1)).addMessage(anyObject());
    }


    @Test
    public void test_getMessage() throws Exception{

        when(messageService.getMessage(anyString())).thenReturn(testMessage);
        RequestBuilder builder = get("/messages/messageid");
        mockMvc.perform(builder);
        verify(messageService, Mockito.times(1)).getMessage("messageid");
    }


    @Test
    public void test_getMessages() throws Exception{
        List<Message> list = Arrays.asList(testMessage,secMessage);
        when(messageService.getMessages()).thenReturn(list);
        RequestBuilder builder = get("/messages");
        mockMvc.perform(builder);
        verify(messageService, Mockito.times(1)).getMessages();
    }


    @Test
    public void test_updateMessage() throws Exception{
        String json = "{\"messageId\":1,\"messageText\":\"hello\",\"sender\":\"Gupta\",\"reciver\":\"naim\"}";
        when(messageService.updateMessage(testMessage)).thenReturn(testMessage);
        RequestBuilder builder = put("/messages").
                contentType(MediaType.APPLICATION_JSON).content(json);

        mockMvc.perform(builder)
                .andExpect(status().isOk());
        verify(messageService, Mockito.times(1)).updateMessage(anyObject());
    }



    @Test
    public void test_deleteMessage() throws Exception{
        when(messageService.deleteMessage("messageId")).thenReturn(true);
        RequestBuilder builder = delete("/messages/messageId");
        mockMvc.perform(builder)
                .andExpect(status().isOk());
        verify(messageService, Mockito.times(1)).deleteMessage("messageId");
    }


    @Test
    public void test_persistMessages() throws Exception{
        when(messageService.persistMessages()).thenReturn(true);
        RequestBuilder builder = put("/messages/persist");
        mockMvc.perform(builder)
                .andExpect(status().isOk());
        verify(messageService, Mockito.times(1)).persistMessages();
    }

}