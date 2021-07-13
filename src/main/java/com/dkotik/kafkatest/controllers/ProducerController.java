package com.dkotik.kafkatest.controllers;

import com.dkotik.kafkatest.dto.MessageWrapper;
import com.dkotik.kafkatest.services.kafka.ProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1")
public class ProducerController {

    private ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/sendmessage")
    public ResponseEntity<Void> sendMessage(@RequestBody MessageWrapper messageWrapper) throws JsonProcessingException {
        producerService.sendMessage(messageWrapper);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
