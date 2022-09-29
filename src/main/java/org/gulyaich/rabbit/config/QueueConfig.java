package org.gulyaich.rabbit.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
public class QueueConfig {

    private final AmqpAdmin amqpAdmin;
    private final List<String> queues;

    public QueueConfig(final AmqpAdmin amqpAdmin,
                       @Value("#{'${queues:}'.split(',')}") final List<String> queues) {
        this.amqpAdmin = amqpAdmin;
        this.queues = queues;
    }

    @PostConstruct
    public void createQueues() {
        log.info("Create queues: {}", queues);
        for (final String queue : queues) {
            amqpAdmin.declareQueue(new Queue(queue.trim(), true));
        }
    }
}
