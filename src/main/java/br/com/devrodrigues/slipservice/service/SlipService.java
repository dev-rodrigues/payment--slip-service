package br.com.devrodrigues.slipservice.service;

import br.com.devrodrigues.slipservice.core.ExternalQueue;
import br.com.devrodrigues.slipservice.core.Slip;
import br.com.devrodrigues.slipservice.core.build.CreateSlipBuilder;
import br.com.devrodrigues.slipservice.core.build.SlipResultBuilder;
import br.com.devrodrigues.slipservice.core.constants.State;
import br.com.devrodrigues.slipservice.repositories.BankRepository;
import br.com.devrodrigues.slipservice.repositories.RabbitRepository;
import br.com.devrodrigues.slipservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SlipService {

    @Value("${queue.intra.exchange}")
    private String exchange;

    @Value("${queue.intra.payment.result.name}")
    private String queueName;

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

    public void execute(Slip messageData) {

        var slip = CreateSlipBuilder
                .builder(userRepository)
                .withBillingId(messageData.getId())
                .withBillingData(messageData.getUserId())
                .withState(State.PROCESSING)
                .withValue(messageData.getValue())
                .withOrderId(messageData.getOrderId())
                .withValue(messageData.getValue())
                .build();

        rabbitRepository.producerOnTopic(
                new ExternalQueue(
                        exchange,
                        routingKey,
                        slip
                )
        );

        var bankResponse = bankRepository.execute(slip);

        var response = SlipResultBuilder
                .builder(bankResponse)
                .withBillingId(messageData.getId())
                .withState(State.PROCESSED)
                .build();

        rabbitRepository.producerOnTopic(
                new ExternalQueue(
                        exchange,
                        routingKey,
                        response
                )
        );
    }
}