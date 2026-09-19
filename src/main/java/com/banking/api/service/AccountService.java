package com.banking.api.service;

import com.banking.api.dto.AccountBalanceRequest;
import com.banking.api.dto.AccountCreationRequest;
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
        return post("api/v1/createAcc", request, "creating account");
    }

    public String checkBalance(AccountBalanceRequest request) {
        log.info("Checking balance for custacno {}", request.getCustacno());
        return post("api/v1/bal", request, "checking account balance");
    }

    public String summaryBalance(AccountBalanceRequest request) {
        log.info("Getting summary balance for custacno {}", request.getCustacno());
        return post("api/v1/Summarybal", request, "getting summary balance");
    }

    public String fullAccountBalance(AccountBalanceRequest request) {
        log.info("Getting full account balance for custacno {}", request.getCustacno());
        return post("api/v1/fullAccbal", request, "getting full account balance");
    }

    public String checkout(AccountBalanceRequest request) {
        log.info("Checking out account for custacno {}", request.getCustacno());
        return post("api/v1/checkout", request, "checking out account");
    }

    public String accountDetails(AccountBalanceRequest request) {
        log.info("Getting account details for custacno {}", request.getCustacno());
        return post("api/v1/AccDetails", request, "getting account details");
    }

    public String statement(AccountBalanceRequest request) {
        log.info("Getting statement for custacno {}", request.getCustacno());
        return post("api/v1/Statement", request, "getting account statement");
    }

    private String post(String uri, Object request, String operation) {
        return webClient
                .post()
                .uri(uri)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    log.error("{} failed: {}", operation, ex.getMessage());
                    return Mono.just("{}");
                })
                .block();
    }
}
