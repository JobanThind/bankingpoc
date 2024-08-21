package com.jobanthind.bankingpoc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CreateAccountResponse {
    Long id;
    String email;
    BigDecimal balance;

}
