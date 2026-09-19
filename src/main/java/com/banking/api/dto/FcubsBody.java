package com.banking.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class FcubsBody {

    private List<ErrorResponse> fcubserrorresp;
    private List<WarningResponse> fcubswarningresp;
    private AccountBalanceResult accbalance;
    private CustomerStatQuery cumulativeIO;
    private CustomerStatFull cumulativeFull;
    private AdhocStatementResult custAccStmtAdhocRequest;
    private MultiJrnlBookFull detbsJrnlTxnMasterFull;
    private CustomerFull customerFull;
}
