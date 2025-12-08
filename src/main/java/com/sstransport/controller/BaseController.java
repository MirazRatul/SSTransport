package com.sstransport.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // base URL
public class BaseController {

    @GetMapping
    public String landing() {
        return "Welcome to SS Transport API!";
    }

    //health check endpoint
    @GetMapping("/health")
    public String health() {
        return "API is running!";
    }
}
