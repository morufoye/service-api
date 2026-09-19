package com.banking.api.controller;

import com.banking.api.dto.AccountBalanceRequest;
import com.banking.api.dto.AccountCreationRequest;
import com.banking.api.dto.AccountDetailsRequest;
import com.banking.api.dto.AccountNumberRequest;
import com.banking.api.dto.AccountResponse;
import com.banking.api.dto.AccountStatsResponse;
import com.banking.api.dto.AuditTrailRequest;
import com.banking.api.dto.CustomerCreateRequest;
import com.banking.api.dto.CustomerQueryRequest;
import com.banking.api.dto.CreateAccountResponse;
import com.banking.api.dto.DebitCreditRequest;
import com.banking.api.dto.FullAccountBalanceResponse;
import com.banking.api.dto.StatementRequest;
import com.banking.api.dto.StatementResponse;
import com.banking.api.dto.SummaryBalanceResponse;
import com.banking.api.dto.TransactionRequest;
import com.banking.api.service.AccountService;
import com.banking.api.service.AccountStatsService;
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
    private final AccountStatsService accountStatsService;

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
    public ResponseEntity<CreateAccountResponse> createAccount(
            @RequestBody AccountCreationRequest request) {

        CreateAccountResponse response = accountService.createAccount(request);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ROLE_CHECK_BALANCE')")
    @PostMapping("/check-balance")
    public ResponseEntity<AccountResponse> checkBalance(
            @RequestBody AccountBalanceRequest request) {

        AccountResponse response = accountService.checkBalance(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/summary-balance")
    public ResponseEntity<SummaryBalanceResponse> summaryBalance(
            @RequestBody AccountNumberRequest request) {
        return ResponseEntity.ok(accountService.summaryBalance(request));
    }

    @PostMapping("/full-account-balance")
    public ResponseEntity<FullAccountBalanceResponse> fullAccountBalance(
            @RequestBody AccountNumberRequest request) {
        return ResponseEntity.ok(accountService.fullAccountBalance(request));
    }

    @PostMapping("/checkout")
    public ResponseEntity<AccountResponse> checkout(
            @RequestBody AccountNumberRequest request) {
        return ResponseEntity.ok(accountService.checkout(request));
    }

    @PostMapping("/account-details")
    public ResponseEntity<AccountResponse> accountDetails(
            @RequestBody AccountDetailsRequest request) {
        return ResponseEntity.ok(accountService.accountDetails(request));
    }

    @PostMapping("/statement")
    public ResponseEntity<StatementResponse> statement(
            @RequestBody StatementRequest request) {
        return ResponseEntity.ok(accountService.statement(request));
    }

    @PostMapping("/customer-stats")
    public ResponseEntity<AccountStatsResponse> queryCustomerStats(
            @RequestBody CustomerQueryRequest request) {
        return ResponseEntity.ok(accountStatsService.queryCustomerStats(request));
    }

    @PostMapping("/audit-trail")
    public ResponseEntity<AccountStatsResponse> queryAuditTrail(
            @RequestBody AuditTrailRequest request) {
        return ResponseEntity.ok(accountStatsService.queryAuditTrail(request));
    }

    @PostMapping("/account-transactions")
    public ResponseEntity<AccountStatsResponse> queryAccountTransaction(
            @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(accountStatsService.queryAccountTransaction(request));
    }
}
