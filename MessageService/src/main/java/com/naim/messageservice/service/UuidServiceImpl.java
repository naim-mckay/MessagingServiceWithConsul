package com.naim.messageservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class UuidServiceImpl implements UuidService {

    String uri = "http://uuid-service/generateid";
    public UuidServiceImpl() {
    }

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String generateUUID(){
        try {
            ResponseEntity res = restTemplate.getForEntity(uri, String.class);
            return (String) res.getBody();
        } catch (Exception e) {
            return generateLocalUUID();
        }
    }

    public String generateLocalUUID(){
        StringBuilder uuidBuilder = new StringBuilder();
        UUID uuid = UUID.randomUUID();
        uuidBuilder.append("LocalUUID-");
        uuidBuilder.append(uuid.toString());
        uuidBuilder.append("-xxx");
        return uuidBuilder.toString();
    }
}
