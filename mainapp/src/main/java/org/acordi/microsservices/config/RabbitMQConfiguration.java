package org.acordi.microsservices.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Queue createPendingProposeQueue(){
        return QueueBuilder.durable("pending-propose.ms-credit-analysis").build();
    }

    @Bean
    public Queue createPendingNotificationQueue(){
        return QueueBuilder.durable("pending-propose.ms-notification").build();
    }

    @Bean
    public Queue createCompletedProposeQueue(){
        return QueueBuilder.durable("completed-propose.ms-credit-analysis").build();
    }

    @Bean
    public Queue createCompletedNotificationQueue(){
        return QueueBuilder.durable("completed-propose.ms-notification").build();
    }
    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public FanoutExchange createFanoutExchange(){
        return ExchangeBuilder.fanoutExchange("pending-propose.ex").build();
    }

    @Bean
    public Binding createBindingPendingPropose(){
        return BindingBuilder.bind(createPendingProposeQueue()).to(createFanoutExchange());
    }


    @Bean
    public Binding createBindingPendingNotification(){
        return BindingBuilder.bind(createPendingNotificationQueue()).to(createFanoutExchange());
    }
}
