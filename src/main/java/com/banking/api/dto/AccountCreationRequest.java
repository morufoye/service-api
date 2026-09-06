package com.banking.api.dto;


import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AccountCreationRequest {

    @NotNull(message = "Branch Code cannot be Empty")
    private String BRN;

    @NotNull(message = "Account number cannot be Empty")
    private String ACC ;


    @NotNull(message = "Customer number cannot be Empty")
    private String CUSTNO ;

    @NotNull(message = "Currency cannot be Empty")
    private String CCY ;

    @NotNull(message = "Account Class cannot be Empty")
    private String ACCLS ;



}
