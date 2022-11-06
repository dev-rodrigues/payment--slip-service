package br.com.devrodrigues.slipservice.service;

import br.com.devrodrigues.slipservice.core.Slip;
import br.com.devrodrigues.slipservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SlipService {

    private final UserRepository userRepository;

    public SlipService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(Slip messageData) {
        var billingData = userRepository.getBillingData(messageData.getUserId());
    }
}