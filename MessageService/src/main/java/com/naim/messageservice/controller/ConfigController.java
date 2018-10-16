package com.naim.messageservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope
@RestController
@Configuration
public class ConfigController {

    public static int MESSAGE_EDIT_TIMER = 10;
    public static int MESSAGE_DELETE_TIMER = 2;

    @Value("${message-service.edit.time.secs:20}")
    public void setMessageEditTimer(int messageEditTimer) {
        MESSAGE_EDIT_TIMER = messageEditTimer;
    }

    @Value("${message-service.delete.time.mins:2}")
    public void setMessageDeleteTimer(int messageDeleteTimer) {
        MESSAGE_DELETE_TIMER = messageDeleteTimer;
    }

    @RequestMapping("/refresh")
    public String getMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append("edit time has been updated to ").append(MESSAGE_EDIT_TIMER);
        builder.append(" delete time has been updated to ").append(MESSAGE_DELETE_TIMER);
        return builder.toString();
    }
}
