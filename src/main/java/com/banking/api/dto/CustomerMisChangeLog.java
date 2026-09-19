package com.banking.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class CustomerMisChangeLog {

    private String miscls;
    private OffsetDateTime txndt;
    private String oldmiscd;
    private String nwmiscd;
}
