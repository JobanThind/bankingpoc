package com.jobanthind.bankingpoc.service;

import com.jobanthind.bankingpoc.dto.CreateAccountRequest;
import com.jobanthind.bankingpoc.dto.CreateAccountResponse;
import com.jobanthind.bankingpoc.exception.AccountAlreadyExistsException;
import com.jobanthind.bankingpoc.model.Account;
import com.jobanthind.bankingpoc.repository.AccountRepository;
import com.jobanthind.bankingpoc.utils.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
public class AccountService {
    private final AccountRepository accountRepository;
    @Autowired
    AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public CreateAccountResponse createAccount(CreateAccountRequest request) {
        if (!InputValidator.emailValidator(request.getEmail())) {
            throw new IllegalArgumentException("email not valid");
        }
        if (accountRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new AccountAlreadyExistsException("Account already exists for email: " + request.getEmail());
        }
        BigDecimal balance = request.getBalance() == null ? BigDecimal.ZERO : request.getBalance();
        Account account = new Account(request.getEmail(), balance);
        Account dbAccount = accountRepository.save(account);
        return CreateAccountResponse.builder()
                .id(dbAccount.getId())
                .email(dbAccount.getEmail())
                .balance(dbAccount.getBalance())
                .build();
    }

}
