package br.com.devrodrigues.slipservice.repositories;

import br.com.devrodrigues.slipservice.core.BankResponse;
import br.com.devrodrigues.slipservice.core.SlipData;

public interface BankRepository {
    BankResponse execute(SlipData slipData);
}
