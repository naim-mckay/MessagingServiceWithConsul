package com.naim.uuidservice.service;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UuidService {
    String generateId();
}