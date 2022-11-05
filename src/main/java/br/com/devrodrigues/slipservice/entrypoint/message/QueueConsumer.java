package br.com.devrodrigues.slipservice.entrypoint.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @RabbitListener(queues = {"${queue.intra.payment.slip.name}"})
    public void receive(@Payload String fileBody) {
        System.out.println("Received: " + fileBody);
    }
}
