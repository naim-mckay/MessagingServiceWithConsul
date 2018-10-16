package com.naim.uuidservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RefreshScope
@RestController
@Configuration
public class ConfigController {

    @Value("${uuid-service.prefix:pre123}")
    private String prefix;

    @Value("${uuid-service.suffix:sux986}")
    private String suffix;

    public String getPrefix() {
        return prefix;
    }
    public String getSuffix() {
        return suffix;
    }

    @RequestMapping("/refresh")
    public String getMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append("prefix has been updated to ").append(prefix);
        builder.append(" suffix has been updated tob").append(suffix);
        return builder.toString();
    }
}
