package com.jobanthind.bankingpoc.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;

@Getter
public class CreateAccountRequestDTO {
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Balance cannot be empty")
    @DecimalMin(value = "1", message = "Balance must be greater than 1")
    private BigDecimal balance;
}
