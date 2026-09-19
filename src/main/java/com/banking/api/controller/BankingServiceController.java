package com.banking.api.controller;

import com.banking.api.dto.AccountBalanceRequest;
import com.banking.api.dto.AccountCreationRequest;
import com.banking.api.dto.AccountDetailsRequest;
import com.banking.api.dto.AccountNumberRequest;
import com.banking.api.dto.AccountResponse;
import com.banking.api.dto.AccountStatsResponse;
import com.banking.api.dto.AccountStatementRequest;
import com.banking.api.dto.AccountStatementResponse;
import com.banking.api.dto.AmtBlockNoRequest;
import com.banking.api.dto.AmountBlockRequest;
import com.banking.api.dto.AuthorizeRequest;
import com.banking.api.dto.CreateTellerRequest;
import com.banking.api.dto.CreateCorporateRequest;
import com.banking.api.dto.CustomerAccountDetailsRequest;
import com.banking.api.dto.CustomerNumberRequest;
import com.banking.api.dto.CustomerResponse;
import com.banking.api.dto.DeResponse;
import com.banking.api.dto.JnrMasterFullTemplate;
import com.banking.api.dto.MultiDeJournalRequest;
import com.banking.api.dto.AuditTrailRequest;
import com.banking.api.dto.CustomerCreateRequest;
import com.banking.api.dto.CustomerQueryRequest;
import com.banking.api.dto.CreateAccountResponse;
import com.banking.api.dto.DebitCreditRequest;
import com.banking.api.dto.FullAccountBalanceResponse;
import com.banking.api.dto.StatementRequest;
import com.banking.api.dto.StatementResponse;
import com.banking.api.dto.StatChangeRequest;
import com.banking.api.dto.StatusChangeResponse;
import com.banking.api.dto.SummaryBalanceResponse;
import com.banking.api.dto.TransactionRequest;
import com.banking.api.dto.AuthorizeTransactionRequest;
import com.banking.api.dto.ProductRequest;
import com.banking.api.dto.ReverseTransactionRequest;
import com.banking.api.dto.RtellerResponse;
import com.banking.api.dto.TransactionQueryRequest;
import com.banking.api.dto.ImageSignatureRequest;
import com.banking.api.dto.ImageSignatureResponse;
import com.banking.api.service.AccountService;
import com.banking.api.service.AccountStatsService;
import com.banking.api.service.AccountFinancialService;
import com.banking.api.service.DeService;
import com.banking.api.service.AccountStatusService;
import com.banking.api.service.CustomerService;
import com.banking.api.service.RtellerService;
import com.banking.api.service.ImageService;
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
    private final AccountFinancialService accountFinancialService;
    private final DeService deService;
    private final AccountStatusService accountStatusService;
    private final ImageService imageService;

    @PreAuthorize("hasAuthority('ROLE_CREATE_CUSTOMER')")
    @PostMapping("/create-customer")
    public ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody CustomerCreateRequest request) {

        CustomerResponse response = customerService.createCustomer(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/query-customer")
    public ResponseEntity<CustomerResponse> queryCustomer(
            @RequestBody CustomerNumberRequest request) {
        return ResponseEntity.ok(customerService.queryCustomer(request));
    }

    @PostMapping("/query-amount-block")
    public ResponseEntity<CustomerResponse> queryAmountBlock(
            @RequestBody AmtBlockNoRequest request) {
        return ResponseEntity.ok(customerService.queryAmountBlock(request));
    }

    @PostMapping("/create-joint-customer")
    public ResponseEntity<CustomerResponse> createJointCustomer(
            @RequestBody CreateCorporateRequest request) {
        return ResponseEntity.ok(customerService.createJointCustomer(request));
    }

    @PostMapping("/create-corporate-customer")
    public ResponseEntity<CustomerResponse> createCorporateCustomer(
            @RequestBody CreateCorporateRequest request) {
        return ResponseEntity.ok(customerService.createCorporateCustomer(request));
    }

    @PostMapping("/amount-block")
    public ResponseEntity<CustomerResponse> amountBlock(
            @RequestBody AmountBlockRequest request) {
        return ResponseEntity.ok(customerService.amountBlock(request));
    }

    @PostMapping("/customer-account-details")
    public ResponseEntity<CustomerResponse> accountDetails(
            @RequestBody CustomerAccountDetailsRequest request) {
        return ResponseEntity.ok(customerService.accountDetails(request));
    }

    @PreAuthorize("hasAuthority('ROLE_PASS_ENTRY')")
    @PostMapping("/pass-entry")
    public ResponseEntity<RtellerResponse> passEntry(
            @RequestBody DebitCreditRequest request) {

        RtellerResponse response = rtellerService.passAccountEntry(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/reverse-transaction")
    public ResponseEntity<RtellerResponse> reverseTransaction(
            @RequestBody ReverseTransactionRequest request) {
        return ResponseEntity.ok(rtellerService.reverseTransaction(request));
    }

    @PostMapping("/query-transaction")
    public ResponseEntity<RtellerResponse> queryTransaction(
            @RequestBody TransactionQueryRequest request) {
        return ResponseEntity.ok(rtellerService.queryTransaction(request));
    }

    @PostMapping("/query-product")
    public ResponseEntity<RtellerResponse> queryProduct(
            @RequestBody ProductRequest request) {
        return ResponseEntity.ok(rtellerService.queryProduct(request));
    }

    @PostMapping("/authorize-transaction")
    public ResponseEntity<RtellerResponse> authorizeTransaction(
            @RequestBody AuthorizeTransactionRequest request) {
        return ResponseEntity.ok(rtellerService.authorizeTransaction(request));
    }

    @PostMapping("/query-image")
    public ResponseEntity<ImageSignatureResponse> queryImage(
            @RequestBody ImageSignatureRequest request) {
        return ResponseEntity.ok(imageService.queryImage(request));
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

    @PostMapping("/customer-statement")
    public ResponseEntity<AccountStatementResponse> queryCustomerStatement(
            @RequestBody AccountStatementRequest request) {
        return ResponseEntity.ok(accountFinancialService.queryCustomerStatement(request));
    }

    @PostMapping("/multi-de-journal")
    public ResponseEntity<DeResponse> multiDeJournal(
            @RequestBody MultiDeJournalRequest request) {
        return ResponseEntity.ok(deService.multiDeJournal(request));
    }

    @PostMapping("/multi-journal-v2")
    public ResponseEntity<DeResponse> multiJournal2(
            @RequestBody MultiDeJournalRequest request) {
        return ResponseEntity.ok(deService.multiJournal2(request));
    }

    @PostMapping("/multi-template")
    public ResponseEntity<DeResponse> multiTemplate(
            @RequestBody JnrMasterFullTemplate request) {
        return ResponseEntity.ok(deService.multiTemplate(request));
    }

    @PostMapping("/create-teller")
    public ResponseEntity<DeResponse> createTeller(
            @RequestBody CreateTellerRequest request) {
        return ResponseEntity.ok(deService.createTeller(request));
    }

    @PostMapping("/authorize")
    public ResponseEntity<DeResponse> authorize(
            @RequestBody AuthorizeRequest request) {
        return ResponseEntity.ok(deService.authorize(request));
    }

    @PostMapping("/account-status-change")
    public ResponseEntity<StatusChangeResponse> changeAccountStatus(
            @RequestBody StatChangeRequest request) {
        return ResponseEntity.ok(accountStatusService.changeAccountStatus(request));
    }
}
