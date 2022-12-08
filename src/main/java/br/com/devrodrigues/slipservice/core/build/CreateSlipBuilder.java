package br.com.devrodrigues.slipservice.core.build;

import br.com.devrodrigues.slipservice.core.BillingData;
import br.com.devrodrigues.slipservice.core.SlipData;
import br.com.devrodrigues.slipservice.core.constants.State;
import br.com.devrodrigues.slipservice.repositories.UserRepository;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import static br.com.devrodrigues.slipservice.core.constants.State.ERROR;

public class CreateSlipBuilder {

    private final UserRepository userRepository;
    private UUID billingId;
    private BillingData billingData;
    private String orderId;
    private String userId;
    private State state;
    private BigDecimal value;

    private CreateSlipBuilder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static CreateSlipBuilder builder(UserRepository userRepository) {
        return new CreateSlipBuilder(userRepository);
    }

    public CreateSlipBuilder withBillingId(UUID id) {
        this.billingId = id;
        return this;
    }

    public CreateSlipBuilder withBillingData(String userId) {
        this.userId = userId;
        this.billingData =  userRepository.getBillingData(userId);
//                new BillingData(
//                userId,
//                "dummy",
//                "21 2222-9033",
//                "rua das flores",
//                "21 24249033"
//        );

        return this;
    }

    public CreateSlipBuilder withState(State state) {
        this.state = state;
        return this;
    }

    public CreateSlipBuilder withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public CreateSlipBuilder withValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public SlipData build() {

        if (Objects.isNull(billingData)) {
            return new SlipData(
                    billingId,
                    orderId,
                    userId,
                    value,
                    ERROR,
                    null
            );
        }

        return new SlipData(
                billingId,
                orderId,
                userId,
                value,
                state,
                billingData
        );
    }
}