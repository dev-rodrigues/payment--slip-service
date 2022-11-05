package br.com.devrodrigues.slipservice.entrypoint.dto;

import br.com.devrodrigues.slipservice.core.Slip;

public class MessageDTO {
    private String exchangeName;
    private String routingKey;
    private Slip messageData;

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public Slip getMessageData() {
        return messageData;
    }

    public void setMessageData(Slip messageData) {
        this.messageData = messageData;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "exchangeName='" + exchangeName + '\'' +
                ", routingKey='" + routingKey + '\'' +
                ", messageData=" + messageData +
                '}';
    }
}
