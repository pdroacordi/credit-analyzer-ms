package org.acordi.creditanalysisms.service;

import org.acordi.creditanalysisms.domain.Proposal;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQNotificationService {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQNotificationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void notify(String exchange, Proposal proposal){
        rabbitTemplate.convertAndSend(exchange, "", proposal);
    }


}
