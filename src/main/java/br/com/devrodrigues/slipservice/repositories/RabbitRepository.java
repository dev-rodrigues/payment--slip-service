package br.com.devrodrigues.slipservice.repositories;


import br.com.devrodrigues.slipservice.core.ExternalQueue;
import br.com.devrodrigues.slipservice.core.SlipData;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RabbitRepository {
    void producerOnTopic(ExternalQueue externalQueue);
    void produceOnQueue(
            String queueName,
            SlipData payload
    ) throws JsonProcessingException;
}