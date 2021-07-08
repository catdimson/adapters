package com.dkotik.kafkatest.controllers;

import com.dkotik.kafkatest.services.kafka.ProducerService;
import com.dkotik.kafkatest.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class BaseController {

    private ProducerService producerService;

    @Autowired
    public BaseController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/generate")
    public String generate(@RequestParam String message, @RequestParam Integer age) {
        producerService.sendMessage(new Message(message, age));
        return "OK";
    }
}
