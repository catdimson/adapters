package com.dkotik.mdm1c.adapter.controllers;

import com.dkotik.mdm1c.adapter.dto.JSONMessageWrapper;
import com.dkotik.mdm1c.adapter.services.ProducerService;
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
    public ResponseEntity<Void> sendMessage(@RequestBody JSONMessageWrapper JSONMessageWrapper) throws JsonProcessingException {
        producerService.sendMessage(JSONMessageWrapper);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
