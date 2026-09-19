package com.banking.api.service;

import com.banking.api.dto.AccountBalanceRequest;
import com.banking.api.dto.AccountCreationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountServiceTest {

    @Test
    void delegatesAccountOperationsToExpectedEndpoints() {
        List<String> paths = new ArrayList<>();
        WebClient webClient = WebClient.builder()
                .baseUrl("http://account-service")
                .exchangeFunction(request -> {
                    paths.add(request.url().getPath());
                    return Mono.just(ClientResponse.create(HttpStatus.OK)
                            .header("Content-Type", "text/plain")
                            .body("ok")
                            .build());
                })
                .build();
        AccountService accountService = new AccountService(webClient);
        AccountBalanceRequest balanceRequest = new AccountBalanceRequest("001", "123456");

        assertEquals("ok", accountService.summaryBalance(balanceRequest));
        assertEquals("ok", accountService.fullAccountBalance(balanceRequest));
        assertEquals("ok", accountService.checkout(balanceRequest));
        assertEquals("ok", accountService.accountDetails(balanceRequest));
        assertEquals("ok", accountService.statement(balanceRequest));
        assertEquals("ok", accountService.checkBalance(balanceRequest));
        assertEquals("ok", accountService.createAccount(
                new AccountCreationRequest("001", "123456", "987654", "NGN", "SAV")));

        assertEquals(List.of(
                "/api/v1/Summarybal",
                "/api/v1/fullAccbal",
                "/api/v1/checkout",
                "/api/v1/AccDetails",
                "/api/v1/Statement",
                "/api/v1/bal",
                "/api/v1/createAcc"), paths);
    }
}
