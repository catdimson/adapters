package com.dkotik.mdm1c.adapter.controllers;

import com.dkotik.mdm1c.adapter.dto.JSONMessage.JSONMessageWrapper;
import com.dkotik.mdm1c.adapter.dto.XMLMessage.XMLMessageWrapper;
import com.dkotik.mdm1c.adapter.services.ProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/sendMessageJSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendMessageJSON(@RequestBody JSONMessageWrapper jsonMessageWrapper) {
        producerService.sendMessage(jsonMessageWrapper);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/sendMessageXML", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Void> sendMessageXML(@RequestBody XMLMessageWrapper xmlMessageWrapper) {
        producerService.sendMessage(xmlMessageWrapper);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

/*
Пример POST JSON-запроса для postman

{
    "body": "содержимое тела",
    "from": "MDM_1C",
    "to": [
        {
            "address": "UPP_1C"
        },
        {
            "address": "TERRASOFT"
        }
    ]
}

*/



/*
Пример POST XML-запроса для postman

<?xml version="1.0" encoding="UTF-8"?>
<message>
    <body>содержимое тела XML</body>
    <from>MDM_1C</from>
    <to>
        <recipient>
            <address>TERRASOFT</address>
        </recipient>
        <recipient>
            <address>UPP_1C</address>
        </recipient>
    </to>
</message>

*/