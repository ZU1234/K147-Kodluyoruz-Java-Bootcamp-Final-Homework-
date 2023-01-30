package com.biletx.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BiletxUserSendSmsQueue {

    private String queueName = "biletx.notification.userSendSms";


    private String exchange = "biletx.notification.userSendSms";


    @Bean
    public Queue userSendSmsQueue() {
        return new Queue(queueName, false);
    }

    @Bean
    public DirectExchange userSendSmsExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding userSendSmsBinding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("");
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }


}
