package br.com.devrodrigues.slipservice.core;

public record ExternalQueue(
        String exchangeName,
        String routingKey,
        SlipData messageData
) {
}