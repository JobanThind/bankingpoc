package com.jobanthind.bankingpoc.controller;

import com.jobanthind.bankingpoc.dto.TransactionHistoryResponseDTO;
import com.jobanthind.bankingpoc.dto.TransferFundsRequestDTO;
import com.jobanthind.bankingpoc.dto.TransferFundsResponseDTO;
import com.jobanthind.bankingpoc.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.jobanthind.bankingpoc.constants.TransactionApiUrls.TRANSACTION_HISTORY_URL;
import static com.jobanthind.bankingpoc.constants.TransactionApiUrls.TRANSFER_FUNDS_URL;

@RestController
@RequestMapping
@Validated
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(TRANSFER_FUNDS_URL)
    public ResponseEntity<TransferFundsResponseDTO> transferFunds(@Valid @RequestBody TransferFundsRequestDTO request) {
        TransferFundsResponseDTO response = transactionService.transferFunds(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(TRANSACTION_HISTORY_URL)
    public ResponseEntity<List<TransactionHistoryResponseDTO>> getTransactionHistory(@PathVariable @Positive Long accountId) {
        List<TransactionHistoryResponseDTO> response = transactionService.getTransactionHistory(accountId)
                .stream()
                .map(transaction -> new TransactionHistoryResponseDTO(
                        transaction.getTxnId(),
                        transaction.getAmount(),
                        transaction.getBalance(),
                        transaction.getTransactionType(),
                        transaction.getStatus(),
                        transaction.getUpdatedAt()
                )).collect(Collectors.toList());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
