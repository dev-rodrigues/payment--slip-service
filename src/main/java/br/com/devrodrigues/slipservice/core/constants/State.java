package br.com.devrodrigues.slipservice.core.constants;

public enum State {
    CREATED,
    WAITING,
    PROCESSING,
    PROCESSED,
    ERROR;

    public static State fromString(String state) {
        return State.valueOf(state.toUpperCase());
    }
}
