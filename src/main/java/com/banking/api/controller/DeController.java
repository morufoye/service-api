package com.banking.api.controller;

import com.banking.api.dto.AuthorizeRequest;
import com.banking.api.dto.CreateTellerRequest;
import com.banking.api.dto.DeResponse;
import com.banking.api.dto.JnrMasterFullTemplate;
import com.banking.api.dto.MultiDeJournalRequest;
import com.banking.api.service.DeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
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
@PreAuthorize("hasAuthority('ROLE_CLIENT')")
public class DeController {

    private final DeService deService;

    @PostMapping("/multi-de-journal")
    public ResponseEntity<DeResponse> multiDeJournal(@RequestBody MultiDeJournalRequest request) {
        return ResponseEntity.ok(deService.multiDeJournal(request));
    }

    @PostMapping("/multi-journal-v2")
    public ResponseEntity<DeResponse> multiJournal2(@RequestBody MultiDeJournalRequest request) {
        return ResponseEntity.ok(deService.multiJournal2(request));
    }

    @PostMapping("/multi-template")
    public ResponseEntity<DeResponse> multiTemplate(@RequestBody JnrMasterFullTemplate request) {
        return ResponseEntity.ok(deService.multiTemplate(request));
    }

    @PostMapping("/create-teller")
    public ResponseEntity<DeResponse> createTeller(@RequestBody CreateTellerRequest request) {
        return ResponseEntity.ok(deService.createTeller(request));
    }

    @PostMapping("/authorize")
    public ResponseEntity<DeResponse> authorize(@RequestBody AuthorizeRequest request) {
        return ResponseEntity.ok(deService.authorize(request));
    }
}
