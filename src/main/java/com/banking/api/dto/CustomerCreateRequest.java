package com.banking.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.databind.JsonNode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateRequest {
  private String addrln1 ;
    private String country;
    private String ccateg;
    private String fullname;
    private String media ;
    private String loc;
      private String sname;
      private String nlty;
      private String uidname;
      private String uidval;
      private String createacc;
      private JsonNode custpersonal;
      private JsonNode custmisfulltype;
}
