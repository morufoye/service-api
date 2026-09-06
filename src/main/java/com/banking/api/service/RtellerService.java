package com.banking.api.service;

import com.banking.api.dto.DebitCreditRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RtellerService {
    private final WebClient webClient;
    public RtellerService(@Qualifier("rtellerServiceWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public String passAccountEntry(DebitCreditRequest request) {
        log.info("Sending request for the batch no: {}", request.getBatchno());

        return webClient
                .post()
                .uri("api/v1/PassAccounting")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    log.error("Pass account entry failed: {}", ex.getMessage());
                    return Mono.just("{}");
                })
                .block();
    }
}
