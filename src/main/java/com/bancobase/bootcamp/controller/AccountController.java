package com.bancobase.bootcamp.controller;

import com.bancobase.bootcamp.dto.AccountDTO;
import com.bancobase.bootcamp.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
@Tag(name = "Account controller")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Get all accounts by customer id")
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccountsByCustomerId(@RequestParam Long customerId) {
        List<AccountDTO> accounts = this.accountService
                .getAllAccountsByCustomerId(customerId);

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @Operation(summary = "Create an account for an existing customer")
    @PostMapping
    public ResponseEntity<List<AccountDTO>> createAccount(Long customerId) {
        List<AccountDTO> accounts = this.accountService
                .createAccountForAnExistingCustomer(customerId);

        return new ResponseEntity<>(accounts, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete an account by account number")
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Void> deleteAccountByAccountNumber(@PathVariable String accountNumber) {
        this.accountService
                .deleteAccountByAccountNumber(accountNumber);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
