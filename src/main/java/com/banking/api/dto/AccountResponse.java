package com.banking.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountResponse {

    private FcubsHeader fcubsheader;
    private FcubsBody fcubsbody;
}
