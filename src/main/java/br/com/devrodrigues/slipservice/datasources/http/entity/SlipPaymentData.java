package br.com.devrodrigues.slipservice.datasources.http.entity;

import br.com.devrodrigues.slipservice.core.SlipData;

import java.math.BigDecimal;

public record SlipPaymentData(
        Long billingId,
        String orderId,
        String userId,
        BigDecimal value,
        String fullName,
        String peopleDocument,
        String address,
        String phone
) {

    public static SlipPaymentData of(SlipData data) {
        return new SlipPaymentData(
                data.billingId(),
                data.orderId(),
                data.userId(),
                data.value(),
                data.billingData().fullName(),
                data.billingData().peopleDocument(),
                data.billingData().address(),
                data.billingData().phone()
        );
    }
}