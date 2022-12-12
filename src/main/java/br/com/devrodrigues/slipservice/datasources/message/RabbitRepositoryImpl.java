package br.com.devrodrigues.slipservice.datasources.message;

import br.com.devrodrigues.slipservice.core.ExternalQueue;
import br.com.devrodrigues.slipservice.core.SlipData;
import br.com.devrodrigues.slipservice.repositories.RabbitRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitRepositoryImpl implements RabbitRepository {

    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper mapper;

    public RabbitRepositoryImpl(AmqpTemplate amqpTemplate, ObjectMapper mapper) {
        this.amqpTemplate = amqpTemplate;
        this.mapper = mapper;
    }

    @Override
    public void producerOnTopic(ExternalQueue externalQueue) {
        try {
            var json = mapper.writeValueAsString(externalQueue.messageData());

            this.amqpTemplate.convertAndSend(
                    externalQueue.exchangeName(),
                    externalQueue.routingKey(),
                    json
            );

        } catch (JsonProcessingException e) {
            // fallback
        }
    }

    @Override
    public void produceOnQueue(String queueName, SlipData payload) throws JsonProcessingException {
        var json = mapper.writeValueAsString(payload);
        this.amqpTemplate.convertAndSend(queueName, json);
    }
}