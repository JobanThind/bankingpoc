package com.jobanthind.bankingpoc.service;

import com.jobanthind.bankingpoc.constants.TransactionType;
import com.jobanthind.bankingpoc.dto.CreateAccountResponse;
import com.jobanthind.bankingpoc.dto.TransferFundsRequest;
import com.jobanthind.bankingpoc.dto.TransferFundsResponse;
import com.jobanthind.bankingpoc.exception.AccountNotFoundException;
import com.jobanthind.bankingpoc.exception.InsufficientBalanceException;
import com.jobanthind.bankingpoc.exception.SameAccountTransferException;
import com.jobanthind.bankingpoc.model.Account;
import com.jobanthind.bankingpoc.model.Transaction;
import com.jobanthind.bankingpoc.repository.AccountRepository;
import com.jobanthind.bankingpoc.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service

public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public TransferFundsResponse transferFunds(@RequestBody TransferFundsRequest request) {
        if(request.getSenderEmail().equals(request.getReceiverEmail())){
            throw new SameAccountTransferException("Sender and receiver email addresses cannot be the same.");
        }
        Account senderAccount = accountRepository.findByEmail(request.getSenderEmail())
                .orElseThrow(() -> new AccountNotFoundException("Account not found for email: " + request.getSenderEmail()));
        BigDecimal amount = request.getAmount();
        if (senderAccount.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientBalanceException("Insufficient balance to complete the transaction.");
        }
        Account receiverAccount = accountRepository.findByEmail(request.getReceiverEmail())
                .orElseThrow(() -> new AccountNotFoundException("Account not found for email: " + request.getReceiverEmail()));

        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
        senderAccount.setUpdatedAt(LocalDateTime.now());
        accountRepository.save(senderAccount);

        receiverAccount.setBalance(receiverAccount.getBalance().add(amount));
        receiverAccount.setUpdatedAt(LocalDateTime.now());
        accountRepository.save(receiverAccount);
        UUID txnId = UUID.randomUUID();
        transactionRepository.save(new Transaction(senderAccount.getId(), TransactionType.DEBIT, amount, "SUCCESS",txnId,senderAccount.getBalance()));
        transactionRepository.save(new Transaction(receiverAccount.getId(), TransactionType.CREDIT, amount, "SUCCESS",txnId,receiverAccount.getBalance()));
        return TransferFundsResponse.builder()
                .txnId(txnId)
                .amount(request.getAmount())
                .senderEmail(request.getSenderEmail())
                .receiverEmail(request.getReceiverEmail())
                .build();
    }

    @Transactional(readOnly = true)
    public List<Transaction> getTransactionHistory(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
