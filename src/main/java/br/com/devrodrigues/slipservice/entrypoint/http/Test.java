package br.com.devrodrigues.slipservice.entrypoint.http;

import br.com.devrodrigues.slipservice.datasources.http.client.UserClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class Test {

    private final UserClient client;

    public Test(UserClient client) {
        this.client = client;
    }

    @GetMapping
    public void teste() {
        client.getBillingData("123");
    }

}
