package br.com.devrodrigues.slipservice.repositories;


import br.com.devrodrigues.slipservice.core.ExternalQueue;

public interface RabbitRepository {
    void producerOnTopic(ExternalQueue externalQueue);
}