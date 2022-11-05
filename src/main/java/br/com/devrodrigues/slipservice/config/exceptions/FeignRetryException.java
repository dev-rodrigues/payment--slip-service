package br.com.devrodrigues.slipservice.config.exceptions;

public class FeignRetryException extends RuntimeException {

    private final int retryMaxAttempt;
    private final long retryInterval;

    public FeignRetryException(int retryMaxAttempt, long retryInterval, String message) {
        super(message);
        this.retryMaxAttempt = retryMaxAttempt;
        this.retryInterval = retryInterval;
    }

    public int getRetryMaxAttempt() {
        return retryMaxAttempt;
    }

    public long getRetryInterval() {
        return retryInterval;
    }
}