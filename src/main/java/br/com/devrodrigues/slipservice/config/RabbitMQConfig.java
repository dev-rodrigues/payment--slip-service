package br.com.devrodrigues.slipservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${queue.intra.payment.slip.name}")
    private String slipQueueName;

    @Bean
    public Queue slipQueue() {
        return new Queue(slipQueueName, true);
    }
}