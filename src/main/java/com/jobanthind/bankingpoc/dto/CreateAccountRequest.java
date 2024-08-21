package com.jobanthind.bankingpoc.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
public class CreateAccountRequest {
    String email;
    BigDecimal balance;
}
