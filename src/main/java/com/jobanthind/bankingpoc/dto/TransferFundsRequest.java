package com.jobanthind.bankingpoc.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TransferFundsRequest {
    private String senderEmail;
    private String receiverEmail;
    private BigDecimal amount;
}
