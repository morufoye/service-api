package com.banking.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class FcubsBody {

    private List<ErrorResponse> fcubserrorresp;
    private List<WarningResponse> fcubswarningresp;
    private AccountBalanceResult accbalance;
}
