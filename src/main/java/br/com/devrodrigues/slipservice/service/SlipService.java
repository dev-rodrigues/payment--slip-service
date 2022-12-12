package br.com.devrodrigues.slipservice.service;

import br.com.devrodrigues.slipservice.core.ExternalQueue;
import br.com.devrodrigues.slipservice.core.Slip;
import br.com.devrodrigues.slipservice.core.build.CreateSlipBuilder;
import br.com.devrodrigues.slipservice.core.build.SlipResultBuilder;
import br.com.devrodrigues.slipservice.core.constants.State;
import br.com.devrodrigues.slipservice.repositories.BankRepository;
import br.com.devrodrigues.slipservice.repositories.RabbitRepository;
import br.com.devrodrigues.slipservice.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class SlipService {

    @Value("${queue.intra.exchange}")
    private String exchange;

    @Value("${queue.intra.payment.result.routing.key}")
    private String routingKey;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BankRepository bankRepository;
    private final UserRepository userRepository;
    private final RabbitRepository rabbitRepository;

    public SlipService(BankRepository bankRepository,
                       UserRepository userRepository,
                       RabbitRepository rabbitRepository) {
        this.bankRepository = bankRepository;
        this.userRepository = userRepository;
        this.rabbitRepository = rabbitRepository;
    }

    public void execute(Slip messageData) throws JsonProcessingException {

        logger.info("Received message: {}", messageData);

        var slip = CreateSlipBuilder
                .builder(userRepository)
                .withBillingId(messageData.getId())
                .withBillingData(messageData.getUserId())
                .withState(State.PROCESSING)
                .withValue(messageData.getValue())
                .withOrderId(messageData.getOrderId())
                .withValue(messageData.getValue())
                .build();


        if (nonNull(slip.billingData())) {

            var bankResponse = bankRepository.execute(slip);

            logger.info("Bank response: {}", bankResponse);

            var response = SlipResultBuilder
                    .builder()
                    .withBillingId(messageData.getId())
                    .withBillingData(slip.billingData())
                    .withValue(slip.value())
                    .withUserId(slip.userId())
                    .withOrderId(slip.orderId())
                    .withState(State.fromString(bankResponse.status()))
                    .build();

            logger.info("Sending response to queue: {}", response);

            rabbitRepository.producerOnTopic(
                    new ExternalQueue(
                            exchange,
                            routingKey,
                            response
                    )
            );

            return;
        }

        // sent to park when integration failure
        rabbitRepository.produceOnQueue(
                "beta.payment.park",
                slip
        );

        logger.info("Sent to park queue: {}", slip);
    }
}