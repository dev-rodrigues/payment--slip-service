package br.com.devrodrigues.slipservice.datasources.http;

import br.com.devrodrigues.slipservice.core.BankResponse;
import br.com.devrodrigues.slipservice.core.SlipData;
import br.com.devrodrigues.slipservice.core.constants.State;
import br.com.devrodrigues.slipservice.datasources.http.client.BankClient;
import br.com.devrodrigues.slipservice.datasources.http.entity.SlipPaymentData;
import br.com.devrodrigues.slipservice.datasources.http.entity.SlipPaymentResponseData;
import br.com.devrodrigues.slipservice.datasources.http.entity.UserPaymentData;
import br.com.devrodrigues.slipservice.repositories.BankRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Component
public class BankRepositoryImpl implements BankRepository {

    private final BankClient client;

    public BankRepositoryImpl(BankClient client) {
        this.client = client;
    }

    @Override
    public BankResponse execute(SlipData slipData) {
        var response = randomResponse(slipData);
        return BankResponse.of(requireNonNull(response.getBody()));
    }

    public ResponseEntity<SlipPaymentResponseData> randomResponse(SlipData slipData) {
        var now = Calendar.getInstance();
        var seconds = now.get(Calendar.MINUTE);
        if (seconds % 2 == 0) {
            return ResponseEntity.ok(new SlipPaymentResponseData(
                    UUID.randomUUID().toString(),
                    State.PROCESSED.toString()
            ));
        }
        return ResponseEntity.ok(new SlipPaymentResponseData(
                UUID.randomUUID().toString(),
                State.ERROR.toString()
        ));
    }
}
