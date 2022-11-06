package br.com.devrodrigues.slipservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${queue.intra.payment.slip.name}")
    private String slipQueueName;

    @Value("${queue.intra.payment.result.name}")
    private String slipQueueResult;

    @Value("${queue.intra.payment.result.routing.key}")
    private String resultRoutingKey;

    @Value("${queue.intra.exchange}")
    public String exchangeName;

    @Bean
    public Queue slipQueue() {
        return new Queue(slipQueueName, true);
    }

    @Bean
    public Queue resultQueue() {
        return new Queue(slipQueueResult, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding resultBinding(Queue resultQueue, DirectExchange exchange) {
        return BindingBuilder.bind(resultQueue).to(exchange).with(resultRoutingKey);
    }
}