package org.gulyaich.rabbit.controller;

import org.gulyaich.rabbit.service.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final ProducerService producerService;

    public MessageController(final ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/send/{queue}")
    public ResponseEntity<Void> send(@RequestBody Object obj, @PathVariable("queue") String queue) {
        producerService.send(queue, obj);
        return ResponseEntity.ok().build();
    }
}
