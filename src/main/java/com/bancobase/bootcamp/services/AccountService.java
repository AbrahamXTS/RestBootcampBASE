package com.bancobase.bootcamp.services;

import com.bancobase.bootcamp.dto.AccountDTO;
import com.bancobase.bootcamp.exceptions.BusinessException;
import com.bancobase.bootcamp.repositories.AccountRepository;
import com.bancobase.bootcamp.repositories.CustomerRepository;
import com.bancobase.bootcamp.schemas.AccountSchema;
import com.bancobase.bootcamp.schemas.CustomerSchema;
import com.bancobase.bootcamp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public AccountDTO getAccountByAccountNumber(String accountNumber) {
        Optional<AccountSchema> account = accountRepository
                .findById(accountNumber);

        return account.map(AccountDTO::getFromSchema).orElse(null);
    }

    public List<AccountDTO> getAllAccountsByCustomerId(Long customerId) {
        return accountRepository
                .findByCustomerCustomerId(customerId)
                .stream()
                .map(AccountDTO::getFromSchema)
                .toList();
    }

    public List<AccountDTO> createAccountForAnExistingCustomer(Long customerId) {
        Optional<CustomerSchema> customer = this
                .customerRepository.findById(customerId);

        if (customer.isEmpty()) {
            throw BusinessException
                    .builder()
                    .message("Requested customer doesn't exist.")
                    .build();
        }

        AccountSchema account = new AccountSchema();

        account.setAccountNumber(Utils.generateAccountNumber());
        account.setCustomer(customer.get());

        AccountSchema savedAccount = accountRepository.save(account);

        return List.of(AccountDTO.getFromSchema(savedAccount));
    }

    public List<AccountSchema> createAccountForANewCustomer(CustomerSchema customer) {
        AccountSchema account = new AccountSchema();

        account.setAccountNumber(Utils.generateAccountNumber());
        account.setCustomer(customer);

        AccountSchema savedAccount = accountRepository.save(account);

        return List.of(savedAccount);
    }
}
