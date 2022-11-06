package br.com.devrodrigues.slipservice.core;

import br.com.devrodrigues.slipservice.core.constants.State;

import java.math.BigDecimal;

public record SlipData(
        String orderId,
        String userId,
        BigDecimal value,
        State state,
        BillingData billingData
) {
}
