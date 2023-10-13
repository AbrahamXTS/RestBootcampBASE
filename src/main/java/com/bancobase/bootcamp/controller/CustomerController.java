package com.bancobase.bootcamp.controller;

import com.bancobase.bootcamp.dto.*;
import com.bancobase.bootcamp.dto.request.PreCustomerInfo;
import com.bancobase.bootcamp.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
@Tag(name = "Customer controller")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Get customer by id")
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long customerId) {
        CustomerDTO customer = this.customerService.getCustomerById(customerId);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @Operation(summary = "Search customers by name")
    @GetMapping
    public ResponseEntity<List<CustomerInfoDTO>> filterCustomersByName(@RequestParam String name) {
        List<CustomerInfoDTO> customers = this.customerService.filterCustomersByName(name);

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @Operation(summary = "Create a new customer")
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody PreCustomerInfo information) {
        CustomerDTO customer = this.customerService.createCustomer(information);

        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
}
