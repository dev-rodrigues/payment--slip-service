package br.com.devrodrigues.slipservice.config;

import br.com.devrodrigues.slipservice.config.exceptions.FeignRetryException;
import feign.RetryableException;
import feign.Retryer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericFeignClientRetryer implements Retryer {
    private final Logger log = LoggerFactory.getLogger(GenericFeignClientRetryer.class);

    protected int retryMaxAttempt;
    protected long retryInterval;
    private int attempt = 1;

    public GenericFeignClientRetryer(int retryMaxAttempt, long retryInterval) {
        this.retryMaxAttempt = retryMaxAttempt;
        this.retryInterval = retryInterval;
    }

    @Override
    public void continueOrPropagate(RetryableException e) {
        log.info("Feign tentando reconectar pela {}° vez, por conta de {} ", attempt, e.getMessage());

        if (attempt++ == retryMaxAttempt)
            throw new FeignRetryException(this.retryMaxAttempt, this.retryInterval, e.getMessage());

        try {
            Thread.sleep(retryInterval);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Retryer clone() { // NOSONAR
        return new GenericFeignClientRetryer(retryMaxAttempt, retryInterval);
    }
}