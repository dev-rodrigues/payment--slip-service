package br.com.devrodrigues.slipservice.core.build;

import br.com.devrodrigues.slipservice.core.BankResponse;
import br.com.devrodrigues.slipservice.core.SlipData;
import br.com.devrodrigues.slipservice.core.constants.State;

public class SlipResultBuilder {

    private final BankResponse bankResponse;
    private SlipResultBuilder(BankResponse bankResponse) {
        this.bankResponse = bankResponse;
    }

    public static SlipResultBuilder builder(BankResponse bankResponse) {
        return new SlipResultBuilder(bankResponse);
    }

    public SlipData build() {
        return new SlipData(
                null,
                null,
                null,
                State.PROCESSED,
                null
        );
    }
}
