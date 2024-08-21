package com.jobanthind.bankingpoc.dto;

import com.jobanthind.bankingpoc.constants.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
public class TransactionHistoryResponseDTO {
    UUID txnId;
    BigDecimal amount;
    BigDecimal balance;
    TransactionType transactionType;
    String status;
    LocalDateTime timeStamp;

    public TransactionHistoryResponseDTO(UUID txnId, BigDecimal amount, BigDecimal balance, TransactionType transactionType, String status, LocalDateTime timeStamp) {
        this.txnId = txnId;
        this.amount = amount;
        this.balance = balance;
        this.transactionType = transactionType;
        this.status = status;
        this.timeStamp = timeStamp;
    }
}
