package com.bancobase.bootcamp.services;

import com.bancobase.bootcamp.dto.AccountDTO;
import com.bancobase.bootcamp.exceptions.*;
import com.bancobase.bootcamp.repositories.*;
import com.bancobase.bootcamp.schemas.*;
import com.bancobase.bootcamp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public List<AccountDTO> getAllAccountsByCustomerId(Long customerId) {
        Optional<CustomerSchema> customer = this
                .customerRepository.findById(customerId);

        if (customer.isEmpty()) {
            throw NotFoundException
                    .builder()
                    .message("Requested customer doesn't exist.")
                    .build();
        }

        return customer.get()
                .getAccounts()
                .stream()
                .map(AccountDTO::getFromSchema)
                .toList();
    }

    public List<AccountSchema> createAccountForANewCustomer(CustomerSchema customer) {
        AccountSchema account = new AccountSchema();

        account.setAccountNumber(Utils.generateAccountNumber());
        account.setCustomer(customer);

        AccountSchema savedAccount = accountRepository.save(account);

        return List.of(savedAccount);
    }

    public List<AccountDTO> createAccountForAnExistingCustomer(Long customerId) {
        Optional<CustomerSchema> customer = this.customerRepository
                .findById(customerId);

        if (customer.isEmpty()) {
            throw NotFoundException
                    .builder()
                    .message("Requested customer doesn't exist.")
                    .build();
        }

        AccountSchema account = new AccountSchema();

        account.setAccountNumber(Utils.generateAccountNumber());
        account.setCustomer(customer.get());

        AccountSchema savedAccount =
                accountRepository.save(account);

        return List.of(AccountDTO.getFromSchema(savedAccount));
    }

    public void deleteAccountByAccountNumber(String accountNumber) {
        Optional<AccountSchema> account = accountRepository
                .findById(accountNumber);

        if (account.isEmpty()) {
            throw NotFoundException
                    .builder()
                    .message("Requested account doesn't exist.")
                    .build();
        }

        if (!(account.get().getBalance().equals(0.0))) {
            throw BusinessException
                    .builder()
                    .message("The account balance must be zero.")
                    .build();
        }

        accountRepository.deleteById(accountNumber);
    }
}
