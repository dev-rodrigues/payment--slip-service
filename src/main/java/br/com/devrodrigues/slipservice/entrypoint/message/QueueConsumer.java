package br.com.devrodrigues.slipservice.entrypoint.message;

import br.com.devrodrigues.slipservice.entrypoint.dto.MessageDTO;
import br.com.devrodrigues.slipservice.service.SlipService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    private final ObjectMapper mapper;
    private final SlipService service;

    public QueueConsumer(ObjectMapper mapper, SlipService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @RabbitListener(queues = {"${queue.intra.payment.slip.name}"})
    public void receive(@Payload String fileBody) throws JsonProcessingException {
        var result = mapper.readValue(fileBody, MessageDTO.class);
        service.execute(result.getMessageData());
    }
}
