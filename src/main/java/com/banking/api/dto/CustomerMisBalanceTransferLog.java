package com.banking.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class CustomerMisBalanceTransferLog {

    private String brn;
    private String deperiodcode;
    private String finyr;
    private OffsetDateTime txndt;
    private String glcode;
    private String miscls;
    private String oldmiscd;
    private String nwmiscd;
    private String trnind;
    private String ccy;
    private BigDecimal amt;
    private BigDecimal xrate;
}
