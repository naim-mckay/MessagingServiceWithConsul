package com.naim.uuidservice.service;


import com.naim.uuidservice.controllers.ConfigController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UuidServiceImpl implements UuidService {

    @Autowired
    ConfigController configController;


    @Override
    public String generateId() {
        String prefix= configController.getPrefix();
        String suffix= configController.getSuffix();
        StringBuilder uuidBuilder = new StringBuilder();
        UUID uuid = UUID.randomUUID();
        uuidBuilder.append(prefix);
        uuidBuilder.append(uuid.toString());
        uuidBuilder.append(suffix);
        return uuidBuilder.toString();
    }
}
