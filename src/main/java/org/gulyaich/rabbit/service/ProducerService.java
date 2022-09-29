package org.gulyaich.rabbit.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerService {
    private final RabbitTemplate rabbitTemplate;

    public ProducerService(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(final String queue, final Object obj) {
        rabbitTemplate.convertSendAndReceive(queue, obj);
        log.info("Sent message to queue {}", queue);
    }
}
