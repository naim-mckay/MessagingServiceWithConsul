package com.naim.messageservice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UuidServiceImplTest {


    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    UuidServiceImpl uuidService;

    ResponseEntity responseEntity = mock(ResponseEntity.class);


    @Test
    public void test_generateUUID() throws Exception{
        when(restTemplate.getForEntity(anyString(),any(Class.class))).thenReturn(responseEntity);
        String res = uuidService.generateUUID();
        verify(restTemplate, Mockito.times(1)).getForEntity(anyString(),any(Class.class));
    }

    @Test
    public void test_generateUUID_WithNoService() throws Exception{
        when(restTemplate.getForEntity(anyString(),any(Class.class))).thenThrow(new MessageException(""));
        String res = uuidService.generateUUID();
        assertTrue(res.startsWith("LocalUUID-"));
    }
}