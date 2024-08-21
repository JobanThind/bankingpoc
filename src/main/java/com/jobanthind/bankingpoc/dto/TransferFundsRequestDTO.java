package com.jobanthind.bankingpoc.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import java.math.BigDecimal;

@Getter
public class TransferFundsRequestDTO {
    @NotEmpty(message = "Sender email cannot be empty")
    @Email(message = "Invalid email format for sender")
    private String senderEmail;

    @NotEmpty(message = "Receiver email cannot be empty")
    @Email(message = "Invalid email format for receiver")
    private String receiverEmail;

    @NotNull(message = "Amount cannot be empty")
    @DecimalMin(value = "1", message = "AMount must be greater than 1")
    private BigDecimal amount;
}
