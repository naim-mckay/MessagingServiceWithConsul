package com.naim.uuidservice.controllers;


import com.naim.uuidservice.service.UuidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UuidController {


    @Autowired
    private UuidService service;

    @GetMapping("/generateid")
    public String getGeneratedId() {
        return service.generateId();
    }

}