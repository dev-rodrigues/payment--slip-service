package br.com.devrodrigues.slipservice.entrypoint.http;

import br.com.devrodrigues.slipservice.core.Slip;
import br.com.devrodrigues.slipservice.core.constants.State;
import br.com.devrodrigues.slipservice.openapi.api.SlipApi;
import br.com.devrodrigues.slipservice.openapi.model.SlipData;
import br.com.devrodrigues.slipservice.service.SlipService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
public class SlipApiImpl implements SlipApi {

    private final SlipService service;

    public SlipApiImpl(SlipService service) {
        this.service = service;
    }

    public ResponseEntity<Void> slipPost(SlipData slipData) {
        try {
            service.execute(
                    new Slip(
                            slipData.getId(),
                            slipData.getUserId(),
                            slipData.getOrderId(),
                            State.fromString(slipData.getState().getValue()),
                            BigDecimal.valueOf(slipData.getValue()),
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    )
            );
        } catch (JsonProcessingException | NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }
}
