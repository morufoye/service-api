package com.banking.api.service;

import com.banking.api.dto.AccountBalanceRequest;
import com.banking.api.dto.AccountCreationRequest;
import com.banking.api.dto.DebitCreditRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AccountService {

    private final WebClient webClient;

    public AccountService(@Qualifier("accountServiceWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public String createAccount(AccountCreationRequest request) {
        log.info("Creating account for customer number {}", request.getCUSTNO());
        return webClient
                .post()
                .uri("api/v1/createAcc")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    log.error("creating account failed: {}", ex.getMessage());
                    return Mono.just("{}");
                })
                .block();
    }

    public String checkBalance(AccountBalanceRequest request) {
        log.info("Checking balance for custacno {}", request.getCustacno());
        return webClient
                .post()
                .uri("api/v1/bal")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    log.error("Checking account balance failed: {}", ex.getMessage());
                    return Mono.just("{}");
                })
                .block();
    }
}
