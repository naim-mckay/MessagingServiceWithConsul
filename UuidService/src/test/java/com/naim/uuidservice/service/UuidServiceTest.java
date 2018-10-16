package com.naim.uuidservice.service;

import com.naim.uuidservice.controllers.ConfigController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UuidServiceTest {

    @Mock
    ConfigController configController;

    @InjectMocks
    UuidService service = new UuidServiceImpl();

    @Test
    public void test_generateId()throws Exception{
        when(configController.getPrefix()).thenReturn("perfix");
        when(configController.getSuffix()).thenReturn("suffix");
        String res = service.generateId();
        assertNotNull(res);
    }
}
