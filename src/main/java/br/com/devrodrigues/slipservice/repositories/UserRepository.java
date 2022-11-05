package br.com.devrodrigues.slipservice.repositories;

import br.com.devrodrigues.slipservice.core.BillingData;

public interface UserRepository {
    BillingData getBillingData(String userId);
}
