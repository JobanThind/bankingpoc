package com.jobanthind.bankingpoc.controller;

import com.jobanthind.bankingpoc.dto.CreateAccountRequestDTO;
import com.jobanthind.bankingpoc.dto.CreateAccountResponseDTO;
import com.jobanthind.bankingpoc.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.jobanthind.bankingpoc.constants.AccountApiUrls.CREATE_ACCOUNT_URL;


@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    AccountController(AccountService accountService){
        this.accountService = accountService;
    }
    @PostMapping(CREATE_ACCOUNT_URL)
    public ResponseEntity<CreateAccountResponseDTO> createAccount(@Valid @RequestBody CreateAccountRequestDTO request) {
        CreateAccountResponseDTO response =  accountService.createAccount(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
