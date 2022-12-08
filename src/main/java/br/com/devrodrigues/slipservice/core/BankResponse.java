package br.com.devrodrigues.slipservice.core;

import br.com.devrodrigues.slipservice.datasources.http.entity.SlipPaymentResponseData;

import java.util.UUID;

public record BankResponse(
        String code,
        String status
) {

    public static BankResponse of(SlipPaymentResponseData response) {
        return  new BankResponse(
                UUID.randomUUID().toString(),
                "PROCESSED"
        );//new BankResponse(response.getCode(), response.getStatus());
    }
}
