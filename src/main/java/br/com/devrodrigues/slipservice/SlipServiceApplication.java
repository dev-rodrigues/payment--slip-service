package br.com.devrodrigues.slipservice;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableRabbit
public class SlipServiceApplication {

    @Bean
    public Queue parkQueue() {
        return new Queue("beta.payment.park", true);
    }

    public static void main(String[] args) {
        SpringApplication.run(SlipServiceApplication.class, args);
    }
}
