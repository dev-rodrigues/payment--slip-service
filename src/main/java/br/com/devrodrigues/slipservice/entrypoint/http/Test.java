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
        var result = client.getBillingData("94e1dda9-747b-4b2b-997e-84c5e4b177f7");
        System.out.println(result);
    }

}
