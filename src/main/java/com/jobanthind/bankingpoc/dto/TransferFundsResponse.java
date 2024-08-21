package com.jobanthind.bankingpoc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
public class TransferFundsResponse {
    private String senderEmail;
    private String receiverEmail;
    private BigDecimal amount;
    private UUID txnId;
}
