package br.com.devrodrigues.slipservice.datasources.http;

import br.com.devrodrigues.slipservice.core.BankResponse;
import br.com.devrodrigues.slipservice.core.SlipData;
import br.com.devrodrigues.slipservice.repositories.BankRepository;
import org.springframework.stereotype.Component;

@Component
public class BankRepositoryImpl implements BankRepository {

    @Override
    public BankResponse execute(SlipData slipData) {
        return null;
    }
}
