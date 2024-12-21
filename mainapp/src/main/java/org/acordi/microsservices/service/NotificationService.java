package org.acordi.microsservices.service;

import org.acordi.microsservices.entity.Proposal;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final RabbitTemplate rabbitTemplate;

    public NotificationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void notify(Proposal proposal, String exchange){
        rabbitTemplate.convertAndSend(exchange, "", proposal);
    }
}
