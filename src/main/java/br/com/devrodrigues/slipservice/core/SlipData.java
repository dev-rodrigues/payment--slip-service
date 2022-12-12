package br.com.devrodrigues.slipservice.core;

import br.com.devrodrigues.slipservice.core.constants.State;

import java.math.BigDecimal;
import java.util.UUID;

public record SlipData(
        UUID billingId,
        String orderId,
        String userId,
        BigDecimal value,
        State state,
        BillingData billingData
) {

    @Override
    public String toString() {
        return "SlipData{" +
                "billingId=" + billingId +
                ", orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", value=" + value +
                ", state=" + state +
                ", billingData=" + billingData +
                '}';
    }
}
