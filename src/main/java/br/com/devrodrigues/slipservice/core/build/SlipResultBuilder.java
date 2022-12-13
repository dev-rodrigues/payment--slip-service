package br.com.devrodrigues.slipservice.core.build;

import br.com.devrodrigues.slipservice.core.BillingData;
import br.com.devrodrigues.slipservice.core.SlipData;
import br.com.devrodrigues.slipservice.core.constants.State;

import java.math.BigDecimal;
import java.util.UUID;

public class SlipResultBuilder {

    private Long billingId;
    private State state;

    private BillingData billingData;

    private BigDecimal value;
    private String userId;
    private String orderId;

    private SlipResultBuilder() {
    }

    public static SlipResultBuilder builder() {
        return new SlipResultBuilder();
    }

    public SlipResultBuilder withBillingId(Long id) {
        this.billingId = id;
        return this;
    }

    public SlipResultBuilder withState(State state) {
        this.state = state;
        return this;
    }


    public SlipResultBuilder withBillingData(BillingData billingData) {
        this.billingData = billingData;
        return this;
    }

    public SlipResultBuilder withValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public SlipResultBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public SlipResultBuilder withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public SlipData build() {
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
