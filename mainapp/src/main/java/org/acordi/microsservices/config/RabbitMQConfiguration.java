package org.acordi.microsservices.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.exchange.proposal.pending}")
    private String pendingExchange;

    @Value("${rabbitmq.exchange.proposal.completed}")
    private String completedExchange;

    @Value("${rabbitmq.exchange.proposal.dead.pending}")
    private String deadPendingExchange;

    @Value("${rabbitmq.exchange.proposal.dead.completed}")
    private String deadCompletedExchange;

    @Bean
    public Queue createPendingProposalQueue(){
        return QueueBuilder.durable("pending-proposal.ms-credit-analysis")
                .deadLetterExchange(deadPendingExchange)
                .build();
    }

    @Bean
    public Queue createPendingNotificationQueue(){
        return QueueBuilder.durable("pending-proposal.ms-notification").build();
    }

    @Bean
    public Queue createPendingProposalDeadLetterQueue(){
        return QueueBuilder.durable("pending-proposal.dlq").build();
    }

    @Bean
    public Queue createCompletedProposalQueue(){
        return QueueBuilder.durable("completed-proposal.ms-credit-analysis")
                .deadLetterExchange(deadCompletedExchange)
                .build();
    }

    @Bean
    public Queue createCompletedNotificationQueue(){
        return QueueBuilder.durable("completed-proposal.ms-notification").build();
    }

    @Bean Queue createCompletedProposalDeadLetterQueue(){
        return QueueBuilder.durable("completed-proposal.dlq").build();
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
    public FanoutExchange createFanoutPendingExchange(){
        return ExchangeBuilder.fanoutExchange(pendingExchange).build();
    }

    @Bean
    public FanoutExchange createFanoutCompletedExchange(){
        return ExchangeBuilder.fanoutExchange(completedExchange).build();
    }

    @Bean
    public FanoutExchange createFanoutPendingDeadLetterExchange(){
        return ExchangeBuilder.fanoutExchange(deadPendingExchange).build();
    }

    @Bean
    public FanoutExchange createFanoutCompletedDeadLetterExchange(){
        return ExchangeBuilder.fanoutExchange(deadCompletedExchange).build();
    }


    @Bean
    public Binding createBindingPendingProposal(){
        return BindingBuilder.bind(createPendingProposalQueue()).to(createFanoutPendingExchange());
    }

    @Bean
    public Binding createBindingCompletedProposal(){
        return BindingBuilder.bind(createCompletedProposalQueue()).to(createFanoutCompletedExchange());
    }

    @Bean
    public Binding createBindingPendingNotification(){
        return BindingBuilder.bind(createPendingNotificationQueue()).to(createFanoutPendingExchange());
    }

    @Bean
    public Binding createBindingCompletedNotification(){
        return BindingBuilder.bind(createCompletedNotificationQueue()).to(createFanoutCompletedExchange());
    }

    @Bean
    public Binding createBindingPendingProposalDeadLetter(){
        return BindingBuilder.bind(createPendingProposalDeadLetterQueue()).to(createFanoutPendingDeadLetterExchange());
    }

    @Bean
    public Binding createBindingCompletedProposalDeadLetter(){
        return BindingBuilder.bind(createCompletedProposalDeadLetterQueue()).to(createFanoutCompletedDeadLetterExchange());
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter( jackson2JsonMessageConverter() );

        return rabbitTemplate;
    }



}
