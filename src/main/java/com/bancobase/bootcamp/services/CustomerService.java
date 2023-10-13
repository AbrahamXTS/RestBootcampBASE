package com.bancobase.bootcamp.services;

import com.bancobase.bootcamp.dto.*;
import com.bancobase.bootcamp.dto.request.PreCustomerInfo;
import com.bancobase.bootcamp.exceptions.*;
import com.bancobase.bootcamp.repositories.CustomerRepository;
import com.bancobase.bootcamp.schemas.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    private final AccountService accountService;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(AccountService accountService, CustomerRepository customerRepository) {
        this.accountService = accountService;
        this.customerRepository = customerRepository;
    }

    public CustomerDTO getCustomerById(Long customerId) {
        Optional<CustomerSchema> customer = this
                .customerRepository.findById(customerId);

        if (customer.isEmpty()) {
            throw NotFoundException
                    .builder()
                    .message("Requested customer doesn't exist.")
                    .build();
        }

        return CustomerDTO
                .builder()
                .information(CustomerInfoDTO.getFromSchema(customer.get()))
                .accounts(customer.get().getAccounts().stream().map(AccountDTO::getFromSchema).toList())
                .build();
    }

    public List<CustomerInfoDTO> filterCustomersByName(String name) {
        return customerRepository
                .findByNameContaining(name)
                .stream()
                .map(CustomerInfoDTO::getFromSchema)
                .toList();
    }

    public CustomerDTO createCustomer(PreCustomerInfo information) {
        if (customerRepository.findByCurp(information.getCurp()).isPresent()) {
            throw BusinessException
                    .builder()
                    .message("A customer with the same CURP " + information.getCurp() + " is already registered")
                    .build();
        }

        CustomerSchema customer = new CustomerSchema();

        customer.setBirthdate(information.getBirthdate());
        customer.setCurp(information.getCurp());
        customer.setGender(information.getGender());
        customer.setName(information.getName());

        CustomerSchema savedCustomer = customerRepository.save(customer);
        List<AccountSchema> accounts = accountService.createAccountForANewCustomer(savedCustomer);

        return CustomerDTO.getFromSchema(customer, accounts);
    }
}
