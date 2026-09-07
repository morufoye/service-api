package com.banking.api.controller;

import com.banking.api.dto.AccountBalanceRequest;
import com.banking.api.dto.AccountCreationRequest;
import com.banking.api.dto.CustomerCreateRequest;
import com.banking.api.dto.DebitCreditRequest;
import com.banking.api.service.AccountService;
import com.banking.api.service.CustomerService;
import com.banking.api.service.RtellerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountException;


@RestController
@RequestMapping("/api/service")
@RequiredArgsConstructor
@SecurityRequirement(name = "keycloak")
@Slf4j
@PreAuthorize("hasAuthority('ROLE_CLIENT')")
public class BankingServiceController {

    private final CustomerService customerService;
    private final RtellerService rtellerService;
    private final AccountService accountService;

    @PreAuthorize("hasAuthority('ROLE_CREATE_CUSTOMER')")
    @PostMapping("/create-customer")
    public ResponseEntity<Boolean> createCustomer(
            @RequestBody CustomerCreateRequest request) {

        boolean response = customerService.createCustomer(request);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ROLE_PASS_ENTRY')")
    @PostMapping("/pass-entry")
    public ResponseEntity<String> passEntry(
            @RequestBody DebitCreditRequest request) {

        String response = rtellerService.passAccountEntry(request);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ROLE_CREATE_ACCOUNT')")
    @PostMapping("/create-account")
    public ResponseEntity<String> createAccount(
            @RequestBody AccountCreationRequest request) {

        String response = accountService.createAccount(request);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ROLE_CHECK_BALANCE')")
    @PostMapping("/check-balance")
    public ResponseEntity<String> checkBalance(
            @RequestBody AccountBalanceRequest request) {

        String response = accountService.checkBalance(request);

        return ResponseEntity.ok(response);
    }
}
