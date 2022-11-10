package br.com.devrodrigues.slipservice.datasources.http;

import br.com.devrodrigues.slipservice.core.BillingData;
import br.com.devrodrigues.slipservice.datasources.http.client.UserClient;
import br.com.devrodrigues.slipservice.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserClient client;

    public UserRepositoryImpl(UserClient client) {
        this.client = client;
    }

    @Override
    public BillingData getBillingData(String userId) {
        var result = client.getBillingData(userId);
        return BillingData.of(
                Objects.requireNonNull(result.getBody()).getUser(),
                result.getBody().getFullName(),
                result.getBody().getPeopleDocument(),
                result.getBody().getAddress(),
                result.getBody().getPhone()
        );
    }
}
