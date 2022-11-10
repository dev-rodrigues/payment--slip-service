package br.com.devrodrigues.slipservice.core.build;

import br.com.devrodrigues.slipservice.core.BankResponse;
import br.com.devrodrigues.slipservice.core.BillingData;
import br.com.devrodrigues.slipservice.core.SlipData;
import br.com.devrodrigues.slipservice.core.constants.State;

import java.util.UUID;

public class SlipResultBuilder {

    private final BankResponse bankResponse;
    private UUID billingId;
    private State state;

    private SlipResultBuilder(BankResponse bankResponse) {
        this.bankResponse = bankResponse;
    }

    public static SlipResultBuilder builder(BankResponse bankResponse) {
        return new SlipResultBuilder(bankResponse);
    }

    public SlipResultBuilder withBillingId(UUID id) {
        this.billingId = id;
        return this;
    }

    public SlipResultBuilder withState(State state) {
        this.state = state;
        return this;
    }

    public SlipData build() {
        return new SlipData(
                billingId,
                null,
                null,
                null,
                State.PROCESSING,
                null
        );
    }
}
