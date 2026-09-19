package com.banking.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class CustomerPersonal {

    private String fstname;
    private String midname;
    private String lstname;
    private OffsetDateTime dob;
    private String gendr;
    private String nationid;
    private String telephno;
    private String lang;
    private String pptno;
    private OffsetDateTime pptissdt;
    private OffsetDateTime pptexpdt;
}
