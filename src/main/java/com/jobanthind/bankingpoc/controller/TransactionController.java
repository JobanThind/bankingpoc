package com.jobanthind.bankingpoc.controller;

import com.jobanthind.bankingpoc.dto.TransactionHistoryResponse;
import com.jobanthind.bankingpoc.dto.TransferFundsRequest;
import com.jobanthind.bankingpoc.dto.TransferFundsResponse;
import com.jobanthind.bankingpoc.model.Transaction;
import com.jobanthind.bankingpoc.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.jobanthind.bankingpoc.constants.TransactionApiUrls.TRANSACTION_HISTORY_URL;
import static com.jobanthind.bankingpoc.constants.TransactionApiUrls.TRANSFER_FUNDS_URL;

@RestController
@RequestMapping
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(TRANSFER_FUNDS_URL)
    public ResponseEntity<TransferFundsResponse> transferFunds(@RequestBody TransferFundsRequest request) {
        TransferFundsResponse response = transactionService.transferFunds(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(TRANSACTION_HISTORY_URL)
    public ResponseEntity<List<TransactionHistoryResponse>> getTransactionHistory(@PathVariable Long accountId) {
        List<TransactionHistoryResponse> response = transactionService.getTransactionHistory(accountId)
                .stream()
                .map(transaction -> new TransactionHistoryResponse(
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
