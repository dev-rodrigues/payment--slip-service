package br.com.devrodrigues.slipservice.datasources.http;

import br.com.devrodrigues.slipservice.core.BillingData;
import br.com.devrodrigues.slipservice.datasources.http.client.UserClient;
import br.com.devrodrigues.slipservice.repositories.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserClient client;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserRepositoryImpl(UserClient client) {
        this.client = client;
    }

    @Override
    @CircuitBreaker(name = "UserRepository", fallbackMethod = "sendToRetry")
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

    public BillingData sendToRetry(Exception e) {
        logger.error(e.getMessage());
        return null;
    }
}
