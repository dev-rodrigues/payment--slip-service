package br.com.devrodrigues.slipservice.datasources.http;

import br.com.devrodrigues.slipservice.core.BillingData;
import br.com.devrodrigues.slipservice.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

    @Override
    public BillingData getBillingData(String userId) {
        return null;
    }
}
