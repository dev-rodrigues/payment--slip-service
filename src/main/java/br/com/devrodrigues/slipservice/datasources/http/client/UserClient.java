package br.com.devrodrigues.slipservice.datasources.http.client;

import br.com.devrodrigues.slipservice.config.FeignRetryerConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "userClient",
        url = "${host.user}",
        configuration = FeignRetryerConfig.class
)
public interface UserClient {
    @GetMapping(value = "/user/{user_id}")
    ResponseEntity<UserClient> getBillingData(@PathVariable("user_id") String userId);
}
