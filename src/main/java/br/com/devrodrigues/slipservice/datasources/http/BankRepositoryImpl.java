package br.com.devrodrigues.slipservice.datasources.http;

import br.com.devrodrigues.slipservice.core.BankResponse;
import br.com.devrodrigues.slipservice.core.SlipData;
import br.com.devrodrigues.slipservice.datasources.http.client.BankClient;
import br.com.devrodrigues.slipservice.datasources.http.entity.SlipPaymentData;
import br.com.devrodrigues.slipservice.repositories.BankRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Component
public class BankRepositoryImpl implements BankRepository {

    private final BankClient client;

    public BankRepositoryImpl(BankClient client) {
        this.client = client;
    }

    @Override
    public BankResponse execute(SlipData slipData) {
        var response = client.paySlip(SlipPaymentData.of(slipData));
        return BankResponse.of(requireNonNull(response.getBody()));
    }
}
