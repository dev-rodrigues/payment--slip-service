package br.com.devrodrigues.slipservice.datasources.http.client;

import br.com.devrodrigues.slipservice.datasources.http.entity.SlipPaymentData;
import br.com.devrodrigues.slipservice.datasources.http.entity.SlipPaymentResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "bank-client",
        url = "${host.mock}"
)
public interface BankClient {
    @PostMapping(value = "/bank/slip", consumes = "application/json", produces = "application/json")
    ResponseEntity<SlipPaymentResponseData> paySlip(
            @RequestBody SlipPaymentData data
    );
}
