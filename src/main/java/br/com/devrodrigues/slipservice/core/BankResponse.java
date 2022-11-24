package br.com.devrodrigues.slipservice.core;

import br.com.devrodrigues.slipservice.datasources.http.entity.SlipPaymentResponseData;

public record BankResponse(
        String code,
        String status
) {

    public static BankResponse of(SlipPaymentResponseData response) {
        return new BankResponse(response.getCode(), response.getStatus());
    }
}
