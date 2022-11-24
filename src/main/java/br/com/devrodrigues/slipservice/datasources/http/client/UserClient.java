package br.com.devrodrigues.slipservice.datasources.http.client;

import br.com.devrodrigues.slipservice.config.FeignRetryerConfig;
import br.com.devrodrigues.slipservice.datasources.http.entity.UserPaymentData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-client",
        url = "${host.mock}",
        configuration = FeignRetryerConfig.class
)
public interface UserClient {
    @GetMapping(value = "/user/{user_id}")
    ResponseEntity<UserPaymentData> getBillingData(@PathVariable("user_id") String userId);
}
