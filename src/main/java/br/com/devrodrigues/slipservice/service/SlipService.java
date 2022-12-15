package br.com.devrodrigues.slipservice.service;

import br.com.devrodrigues.slipservice.core.ExternalQueue;
import br.com.devrodrigues.slipservice.core.Slip;
import br.com.devrodrigues.slipservice.core.SlipData;
import br.com.devrodrigues.slipservice.core.build.CreateSlipBuilder;
import br.com.devrodrigues.slipservice.core.build.SlipResultBuilder;
import br.com.devrodrigues.slipservice.core.constants.State;
import br.com.devrodrigues.slipservice.repositories.BankRepository;
import br.com.devrodrigues.slipservice.repositories.RabbitRepository;
import br.com.devrodrigues.slipservice.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class SlipService {

    @Value("${queue.intra.exchange}")
    private String exchange;

    @Value("${queue.intra.payment.result.routing.key}")
    private String routingKey;

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

    public Pair<SlipData, SlipData> execute(Slip messageData) throws JsonProcessingException {

        System.out.println("Received message: " + messageData);

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

            System.out.println("Bank response: " + bankResponse);

            var response = SlipResultBuilder
                    .builder()
                    .withBillingId(messageData.getId())
                    .withBillingData(slip.billingData())
                    .withValue(slip.value())
                    .withUserId(slip.userId())
                    .withOrderId(slip.orderId())
                    .withState(State.fromString(bankResponse.status()))
                    .build();

            System.out.println("Sending response to queue: " + response);

            rabbitRepository.producerOnTopic(
                    new ExternalQueue(
                            exchange,
                            routingKey,
                            response
                    )
            );

            return Pair.of(slip, response);
        }

        // sent to park when integration failure
        rabbitRepository.produceOnQueue(
                "beta.payment.park",
                slip
        );

        System.out.println("Sent to park: " + slip);
        return null;
    }
}